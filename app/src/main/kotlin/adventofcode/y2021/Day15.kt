package adventofcode.y2021

import adventofcode.Days
import adventofcode.matrix.*

class Day15 : Days() {
    private fun parseInput(input: String): IntMap {
        return input.lines().filter { it.isNotEmpty() }
            .map { line ->
                line.toCharArray()
                    .toTypedArray()
                    .map { c -> c.digitToInt() }
                    .toTypedArray()
            }
            .toTypedArray()
    }

    private fun generateNewMap(map: IntMap): IntMap {
        val mapSize = getSize(map)

        val newMap = Array(mapSize.row() * 5) { rowIndex ->
            Array(mapSize.col() * 5) { colIndex ->
                val rowPortionIndex = rowIndex / mapSize.row()
                val colPortionIndex = colIndex / mapSize.col()

                val originalRowIndex = rowIndex % mapSize.row()
                val originalColIndex = colIndex % mapSize.col()

                val originValue = map[originalRowIndex][originalColIndex]

                var newValue = originValue + (rowPortionIndex + colPortionIndex)

                if (newValue > 9) {
                    newValue -= 9
                }

                newValue
            }
        }

        return newMap
    }

    private fun computeDistances(map: IntMap): IntMap {
        val mapSize = getSize(map)

        val distanceMap = intMap(mapSize, Int.MAX_VALUE)
        distanceMap[Location(0, 0)] = 0

        val chosenMap = generateBooleanMap(mapSize)
        val distanceComputed = mutableSetOf(Location(0, 0))

        while (true) {
            val nextChosen = distanceComputed.minByOrNull { distanceMap[it] }!!
            chosenMap[nextChosen] = true
            distanceComputed.remove(nextChosen)

            listOf(
                nextChosen.top(),
                nextChosen.bottom(),
                nextChosen.left(),
                nextChosen.right()
            )
                .filter { !it.outOfBound(mapSize) }
                .filter { !chosenMap[it] && distanceMap[it] == Int.MAX_VALUE }
                .forEach {
                    distanceMap[it] = distanceMap[nextChosen] + map[it]
                    distanceComputed.add(it)
                    if (it.row() == mapSize.row() - 1 && it.col() == mapSize.col() - 1) {
                        return distanceMap
                    }
                }
        }
    }

    override fun runPartOneWith(input: String): String {
        val map = parseInput(input)
        val mapSize = getSize(map)

        val distanceMap = computeDistances(map)

        return distanceMap[Location(mapSize.row() - 1, mapSize.col() - 1)].toString()
    }

    override fun runPartTwoWith(input: String): String {
        val map = parseInput(input)

        val newMap = generateNewMap(map)
        val newMapSize = getSize(newMap)

        val distanceMap = computeDistances(newMap)

        return distanceMap[Location(newMapSize.row() - 1, newMapSize.col() - 1)].toString()
    }
}