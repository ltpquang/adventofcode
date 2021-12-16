package adventofcode.y2021

import adventofcode.Days
import adventofcode.matrix.Location
import adventofcode.matrix.generateBooleanMap
import adventofcode.matrix.stringify

class Day13 : Days() {
    private enum class Direction {
        HORIZONTAL,
        VERTICAL
    }

    private data class FoldInstruction(val position: Int, val foldLine: Direction) {}

    private fun parseInput(input: String): Pair<MutableList<Location>, MutableList<FoldInstruction>> {

        val locs = mutableListOf<Location>()

        val foldIns = mutableListOf<FoldInstruction>()

        input.lines().filter { it.isNotEmpty() }
            .forEach {
                if (it.contains(",")) {
                    val nums = it.split(",").map { num -> num.toInt() }
                    locs.add(Location(nums[0], nums[1]))
                } else {
                    foldIns.add(
                        FoldInstruction(
                            it.split("=")[1].toInt(),
                            if (it.contains("x")) Direction.VERTICAL else Direction.HORIZONTAL
                        )
                    )
                }
            }

        return Pair(locs, foldIns)
    }

    private fun fold(point: Location, ins: FoldInstruction): Location {
        return if (ins.foldLine == Direction.VERTICAL && point.first > ins.position) {
            Location(ins.position - (point.first - ins.position), point.second)
        } else if (ins.foldLine == Direction.HORIZONTAL && point.second > ins.position) {
            Location(point.first, ins.position - (point.second - ins.position))
        } else {
            point
        }
    }

    private fun foldAll(points: List<Location>, ins: FoldInstruction): List<Location> {
        return points.map { fold(it, ins) }.toSet().toList()
    }

    override fun runPartOneWith(input: String): String {
        val data = parseInput(input)
        val locs = data.first
        val ins = data.second

        val firstIns = ins.first()

        val result = foldAll(locs, firstIns)
        return result.size.toString()
    }

    override fun runPartTwoWith(input: String): String {
        val data = parseInput(input)
        val locs = data.first
        val ins = data.second

        var result = locs.toList()


        ins.forEach { i -> result = foldAll(result, i) }

        val width = result.maxOf { it.first } + 1
        val height = result.maxOf { it.second } + 1

        val boolMap = generateBooleanMap(Pair(height, width))

        result.forEach { boolMap[it.second][it.first] = true }

        return boolMap.stringify('#', '.', "")
    }
}