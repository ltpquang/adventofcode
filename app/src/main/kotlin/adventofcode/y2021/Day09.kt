package adventofcode.y2021

import adventofcode.Days
import adventofcode.matrix.*

class Day09 : Days() {
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

    private fun isLocallyLowest(row: Int, col: Int, map: Array<Array<Int>>): Boolean {
        val it = map[row][col]
        return it < getUp(row, col, map, Int.MAX_VALUE)
                && it < getDown(row, col, map, Int.MAX_VALUE)
                && it < getLeft(row, col, map, Int.MAX_VALUE)
                && it < getRight(row, col, map, Int.MAX_VALUE)
    }

    private fun getUp(row: Int, col: Int, map: Array<Array<Int>>, default: Int): Int {
        val resultRow = row - 1
        val resultCol = col
        if (resultRow < 0) {
            return default
        }
        return map[resultRow][resultCol]
    }

    private fun getDown(row: Int, col: Int, map: Array<Array<Int>>, default: Int): Int {
        val resultRow = row + 1
        val resultCol = col
        if (resultRow == getSize(map).first) {
            return default
        }
        return map[resultRow][resultCol]
    }

    private fun getLeft(row: Int, col: Int, map: Array<Array<Int>>, default: Int): Int {
        val resultRow = row
        val resultCol = col - 1
        if (resultCol < 0) {
            return default
        }
        return map[resultRow][resultCol]
    }

    private fun getRight(row: Int, col: Int, map: Array<Array<Int>>, default: Int): Int {
        val resultRow = row
        val resultCol = col + 1
        if (resultCol == getSize(map).second) {
            return default
        }
        return map[resultRow][resultCol]
    }

    private fun markBasin(
        previous: Int,
        point: Pair<Int, Int>,
        map: Array<Array<Int>>,
        basin: Array<Array<Boolean>>
    ): Array<Array<Boolean>> {
        if (point.outOfBound(getSize(map))) {
            return basin
        }
        if (basin[point.row()][point.col()]) {
            return basin
        }
        val currentValue = map[point.row()][point.col()]
        if (currentValue == 9) {
            return basin
        }
        if (currentValue < previous) {
            return basin
        }

        basin[point.row()][point.col()] = true

        markBasin(currentValue, point.top(), map, basin)
        markBasin(currentValue, point.bottom(), map, basin)
        markBasin(currentValue, point.left(), map, basin)
        markBasin(currentValue, point.right(), map, basin)

        return basin
    }

    override fun runPartOneWith(input: String): String {
        val map = parseInput(input)
        val points = mutableListOf<Pair<Int, Int>>()

        val mapSize = getSize(map)

        for (row in 0 until mapSize.first) {
            for (col in 0 until mapSize.second) {
                if (isLocallyLowest(row, col, map)) {
                    points.add(Pair(row, col))
                }
            }
        }

        val values = points
            .map { map[it.row()][it.col()] }
            .sumOf { it + 1 }

        return values.toString()
    }

    override fun runPartTwoWith(input: String): String {
        val map = parseInput(input)
        val points = mutableListOf<Pair<Int, Int>>()

        val mapSize = getSize(map)

        for (row in 0 until mapSize.first) {
            for (col in 0 until mapSize.second) {
                if (isLocallyLowest(row, col, map)) {
                    points.add(Pair(row, col))
                }
            }
        }

        var result = 1

        points
            .map { markBasin(Int.MIN_VALUE, it, map, generateBooleanMap(getSize(map))) }
            .map { it.countTrue() }
            .sortedDescending()
            .take(3)
            .forEach { result *= it }

        return result.toString()
    }
}
