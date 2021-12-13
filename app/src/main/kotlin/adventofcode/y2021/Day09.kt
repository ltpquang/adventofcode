package adventofcode.y2021

import adventofcode.Days

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

    private fun generateBasin(mapSize: Pair<Int, Int>): Array<Array<Boolean>> {
        return Array(mapSize.first) {
            Array(mapSize.second) { false }
        }
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
        if (resultRow == getMapSize(map).first) {
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
        if (resultCol == getMapSize(map).second) {
            return default
        }
        return map[resultRow][resultCol]
    }

    private fun getMapSize(map: Array<Array<Int>>): Pair<Int, Int> {
        return Pair(map.size, map[0].size)
    }

    private fun markBasin(
        previous: Int,
        point: Pair<Int, Int>,
        map: Array<Array<Int>>,
        basin: Array<Array<Boolean>>
    ): Array<Array<Boolean>> {
        if (point.outOfBound(getMapSize(map))) {
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

        markBasin(currentValue, point.up(), map, basin)
        markBasin(currentValue, point.down(), map, basin)
        markBasin(currentValue, point.left(), map, basin)
        markBasin(currentValue, point.right(), map, basin)

        return basin
    }

    override fun runPartOneWith(input: String): String {
        val map = parseInput(input)
        val points = mutableListOf<Pair<Int, Int>>()

        val mapSize = getMapSize(map)

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

        val mapSize = getMapSize(map)

        for (row in 0 until mapSize.first) {
            for (col in 0 until mapSize.second) {
                if (isLocallyLowest(row, col, map)) {
                    points.add(Pair(row, col))
                }
            }
        }

        var result = 1

        points
            .map { markBasin(Int.MIN_VALUE, it, map, generateBasin(getMapSize(map))) }
            .map { basinSize(it) }
            .sortedDescending()
            .take(3)
            .forEach { result *= it }

        return result.toString()
    }

    private fun basinSize(basin: Array<Array<Boolean>>): Int {
        return basin.flatMap { it.asList() }.count { it }
    }
}

private fun Pair<Int, Int>.outOfBound(size: Pair<Int, Int>): Boolean {
    return first < 0 || second < 0 || first == size.first || second == size.second
}

private fun Pair<Int, Int>.row(): Int {
    return first
}

private fun Pair<Int, Int>.col(): Int {
    return second
}

private fun Pair<Int, Int>.up(): Pair<Int, Int> {
    return Pair(first - 1, second)
}

private fun Pair<Int, Int>.down(): Pair<Int, Int> {
    return Pair(first + 1, second)
}

private fun Pair<Int, Int>.left(): Pair<Int, Int> {
    return Pair(first, second - 1)
}

private fun Pair<Int, Int>.right(): Pair<Int, Int> {
    return Pair(first, second + 1)
}
