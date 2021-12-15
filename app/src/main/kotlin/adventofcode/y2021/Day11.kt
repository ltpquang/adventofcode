package adventofcode.y2021

import adventofcode.Days
import adventofcode.matrix.*

class Day11 : Days() {
    private fun parseInput(input: String): Array<Array<Int>> {
        return input.lines().filter { it.isNotEmpty() }
            .map { line ->
                line.toCharArray()
                    .toTypedArray()
                    .map { c -> c.digitToInt() }
                    .toTypedArray()
            }
            .toTypedArray()
    }

    private fun gainEnergy(octopus: Location, map: IntMap, flashedMap: Array<Array<Boolean>>): IntMap {
        if (octopus.outOfBound(getSize(map))) {
            return map
        }
        if (flashedMap[octopus]) {
            return map
        }

        map[octopus] += 1

        if (map[octopus] <= 9) {
            return map
        }

        map[octopus] = 0
        flashedMap[octopus] = true

        gainEnergy(octopus.top(), map, flashedMap)
        gainEnergy(octopus.bottom(), map, flashedMap)
        gainEnergy(octopus.left(), map, flashedMap)
        gainEnergy(octopus.right(), map, flashedMap)
        gainEnergy(octopus.top().left(), map, flashedMap)
        gainEnergy(octopus.top().right(), map, flashedMap)
        gainEnergy(octopus.bottom().left(), map, flashedMap)
        gainEnergy(octopus.bottom().right(), map, flashedMap)

        return map
    }

    override fun runPartOneWith(input: String): String {
        val map = parseInput(input)
        var result = 0
        repeat(100) {
            val flashedMap = generateBooleanMap(getSize(map))
            map.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { colIndex, _ ->
                    gainEnergy(Pair(rowIndex, colIndex), map, flashedMap)
                }
            }
            result += flashedMap.countTrue()
        }
        return result.toString()
    }

    override fun runPartTwoWith(input: String): String {
        val map = parseInput(input)
        var result = 0

        while (true) {
            val size = getSize(map)
            val flashedMap = generateBooleanMap(size)
            map.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { colIndex, _ ->
                    gainEnergy(Pair(rowIndex, colIndex), map, flashedMap)
                }
            }
            result += 1
            if (flashedMap.countTrue() == size.row() * size.col()) {
                break
            }
        }
        return result.toString()
    }
}