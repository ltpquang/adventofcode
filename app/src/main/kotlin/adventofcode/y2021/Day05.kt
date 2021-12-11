package adventofcode.y2021

import adventofcode.Days
import kotlin.math.max
import kotlin.math.min

class Day05 : Days() {
    private enum class Direction {
        VERTICAL,
        HORIZONTAL,
        DIAGONAL,
        DIAGONAL_INVERSE
    }

    private class Point(val x: Int, val y: Int) {
        companion object {
            fun parse(pointData: String): Point {
                return Point(
                    x = pointData.split(",")[0].toInt(),
                    y = pointData.split(",")[1].toInt()
                )
            }
        }

        override fun toString(): String {
            return "($x, $y)"
        }
    }

    private class Segment private constructor(
        val head: Point,
        val tail: Point,
        val direction: Direction
    ) {
        companion object {
            fun parse(lineData: String): Segment {
                val head = Point.parse(lineData.split(" -> ")[0])
                val tail = Point.parse(lineData.split(" -> ")[1])
                val direction = when {
                    head.x == tail.x -> Direction.VERTICAL
                    head.y == tail.y -> Direction.HORIZONTAL
                    (head.x - tail.x) / (head.y - tail.y) > 0 -> Direction.DIAGONAL
                    else -> Direction.DIAGONAL_INVERSE
                }
                return Segment(head, tail, direction)
            }
        }

        fun getPoints(): List<Point> {
            return when (this.direction) {
                Direction.HORIZONTAL -> (min(head.x, tail.x)..max(head.x, tail.x))
                    .map { Point(it, head.y) }
                Direction.VERTICAL -> (min(head.y, tail.y)..max(head.y, tail.y))
                    .map { Point(head.x, it) }
                Direction.DIAGONAL ->
                    ((min(head.x, tail.x)..max(head.x, tail.x)) zip (min(head.y, tail.y)..max(head.y, tail.y)))
                        .map { Point(it.first, it.second) }
                Direction.DIAGONAL_INVERSE ->
                    ((min(head.x, tail.x)..max(head.x, tail.x)) zip (min(head.y, tail.y)..max(head.y, tail.y)).reversed())
                        .map { Point(it.first, it.second) }
            }
        }
    }

    override fun runPartOneWith(input: String): String {
        val result = HashMap<String, Int>()
        input.lines()
            .filter { it.isNotEmpty() }
            .map { Segment.parse(it) }
            .filter { it.direction == Direction.HORIZONTAL || it.direction == Direction.VERTICAL }
            .flatMap { it.getPoints() }
            .forEach {
                val key = it.toString()
                val count = result.getOrDefault(key, 0) + 1
                result[key] = count
            }

        return result.filter { it.value > 1 }.size.toString()
    }

    override fun runPartTwoWith(input: String): String {
        val result = HashMap<String, Int>()
        input.lines()
            .filter { it.isNotEmpty() }
            .map { Segment.parse(it) }
            .flatMap { it.getPoints() }
            .forEach {
                val key = it.toString()
                val count = result.getOrDefault(key, 0) + 1
                result[key] = count
            }

        return result.filter { it.value > 1 }.size.toString()
    }
}