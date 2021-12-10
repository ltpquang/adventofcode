package adventofcode.y2021

import adventofcode.Days
import java.util.*

class Day02 : Days() {
    private enum class Direction {
        FORWARD, DOWN, UP
    }

    private class Movement private constructor(
        val direction: Direction,
        val value: Int
    ) {
        companion object {
            fun parse(data: String): Movement {
                val move = data.split(" ")
                return Movement(Direction.valueOf(move[0].uppercase(Locale.getDefault())), move[1].toInt())
            }
        }
    }

    private open class Position(open var x: Int, open var y: Int) {
        open fun move(m: Movement) {
            when (m.direction) {
                Direction.FORWARD -> x += m.value
                Direction.DOWN -> y -= m.value
                Direction.UP -> y += m.value
            }
        }
    }

    private class PositionWithAim(override var x: Int, override var y: Int, var aim: Int): Position(x, y) {
        override fun move(m: Movement) {
            when (m.direction) {
                Direction.FORWARD -> {
                    x += m.value
                    y -= m.value * aim
                }
                Direction.DOWN -> aim -= m.value
                Direction.UP -> aim += m.value
            }
        }
    }

    override fun runPartOneWith(input: String): String {
        val result = Position(0, 0)
        input
            .lines()
            .filter { it.isNotEmpty() }
            .map { Movement.parse(it) }
            .forEach { result.move(it) }
        return (result.x * result.y * -1).toString()
    }

    override fun runPartTwoWith(input: String): String {
        val result = PositionWithAim(0, 0, 0)
        input
            .lines()
            .filter { it.isNotEmpty() }
            .map { Movement.parse(it) }
            .forEach { result.move(it) }
        return (result.x * result.y).toString()
    }
}