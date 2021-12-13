package adventofcode.y2021

import adventofcode.Days

class Day08 : Days() {
    private class Digit(private val segments: CharArray) {
        val segmentCount = segments.size

        fun containsAllSegmentsOf(other: Digit): Boolean {
            val notPresentSegment = other.segments.firstOrNull { it !in segments }
            return notPresentSegment == null
        }

        fun merge(other: Digit): Digit {
            val allSegments = segments.toMutableSet()
            allSegments.addAll(other.segments.toList())
            return Digit(allSegments.joinToString("").toCharArray())
        }

        fun sameAs(other: Digit): Boolean {
            return segments.toSet() == other.segments.toSet()
        }
    }

    private class Dictionary(digits: List<Digit>) {
        private val data = Array(10) { Digit("".toCharArray()) }

        init {
            val input = digits.toMutableList()

            register(1, input.first { it.segmentCount == 2 })
            input.remove(data[1])

            register(4, input.first { it.segmentCount == 4 })
            input.remove(data[4])

            register(7, input.first { it.segmentCount == 3 })
            input.remove(data[7])

            register(8, input.first { it.segmentCount == 7 })
            input.remove(data[8])

            register(3, input.first { it.segmentCount == 5 && it.containsAllSegmentsOf(data[1]) })
            input.remove(data[3])

            register(9, input.first { it.segmentCount == 6 && it.containsAllSegmentsOf(data[3]) })
            input.remove(data[9])

            register(0, input.first { it.segmentCount == 6 && it.containsAllSegmentsOf(data[1]) })
            input.remove(data[0])

            register(6, input.first { it.segmentCount == 6 })
            input.remove(data[6])

            register(5, input.first { it.merge(data[1]).sameAs(data[9]) })
            input.remove(data[5])

            register(2, input.first())
        }

        private fun register(value: Int, digit: Digit) {
            data[value] = digit
        }

        fun translateToNumber(input: String): Int {
            return input.split(" ")
                .map { Digit(it.toCharArray()) }
                .map { data.indexOfFirst { i -> i.sameAs(it) } }
                .joinToString("") { it.toString() }
                .toInt()
        }
    }

    override fun runPartOneWith(input: String): String {
        val result = input.lines().filter { it.isNotEmpty() }
            .flatMap {
                it.split("|")[1].trim().split(" ")
            }
            .count { it.length in arrayOf(2, 3, 4, 7) }

        return result.toString()
    }

    override fun runPartTwoWith(input: String): String {
        val result = input.lines().filter { it.isNotEmpty() }
            .map { it.split(" | ") }
            .map {
                Pair(
                    Dictionary(it[0].split(" ").map { token -> Digit(token.toCharArray()) }),
                    it[1]
                )
            }
            .sumOf { it.first.translateToNumber(it.second) }

        return result.toString()
    }
}