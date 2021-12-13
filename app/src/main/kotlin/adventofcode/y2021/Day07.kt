package adventofcode.y2021

import adventofcode.Days
import kotlin.math.abs

class Day07 : Days() {
    override fun runPartOneWith(input: String): String {
        val crabs = input.trim().split(",")
            .map { it.toInt() }

        val crabsSet = crabs.toSet()

        val minFuel = crabsSet
            .map { crabSet ->
                crabs.sumOf { abs(it - crabSet) }
            }
            .minOf { it }

        return minFuel.toString()
    }

    override fun runPartTwoWith(input: String): String {
        val crabs = input.trim().split(",")
            .map { it.toInt() }

        val crabsSet = crabs.toSet()

        val minFuel = crabsSet
            .map { crabSet ->
                crabs.sumOf { it ->
                    val stepsCount = abs(it - crabSet)
                    generateSequence(1) { it + 1 }
                        .take(stepsCount)
                        .sum()
                }
            }
            .minOf { it }

        return minFuel.toString()
    }
}
