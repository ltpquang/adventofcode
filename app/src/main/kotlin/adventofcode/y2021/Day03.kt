package adventofcode.y2021

import adventofcode.Days

class Day03 : Days() {
    override fun runPartOneWith(input: String): String {
        val mostCommonBits = getMostCommonBits(input.lines())
        val gammaRateBinString = mostCommonBits.joinToString(separator = "")
        val gammaRate = Integer.parseInt(gammaRateBinString, 2)
        val epsilonRate = gammaRate.xor(Integer.parseInt("1".repeat(input.lines().first().length), 2))
        return (gammaRate * epsilonRate).toString()
    }

    override fun runPartTwoWith(input: String): String {
        val maxLength = input.lines().first().length

        var lines = input.lines().filter { it.isNotEmpty() }
        var mostCommonBits = getMostCommonBits(lines)
        for (i in 0 until maxLength) {
            lines = lines.filter { it[i] == mostCommonBits[i].toString()[0] }
            if (lines.size == 1) {
                break
            }
            mostCommonBits = getMostCommonBits(lines)
        }
        val o2Rating = Integer.parseInt(lines.first(), 2)


        lines = input.lines().filter { it.isNotEmpty() }
        var leastCommonBits = getLeastCommonBits(lines)
        for (i in 0 until maxLength) {
            lines = lines.filter { it[i] == leastCommonBits[i].toString()[0] }
            if (lines.size == 1) {
                break
            }
            leastCommonBits = getLeastCommonBits(lines)
        }
        val co2Rating = Integer.parseInt(lines.first(), 2)

        return (o2Rating * co2Rating).toString()
    }

    private fun getMostCommonBits(input: List<String>): List<Int> {
        val binaryLength = input.first().length
        val sums = IntArray(binaryLength) { 0 }
        input
            .map { it.toCharArray() }
            .forEach {
                it.forEachIndexed { index, c ->
                    if (c == '0') {
                        sums[index] -= 1
                    } else {
                        sums[index] += 1
                    }
                }
            }
        return sums.map { if (it >= 0) 1 else 0 }
    }

    private fun getLeastCommonBits(input: List<String>): List<Int> {
        return getMostCommonBits(input)
            .map { if (it == 0) 1 else 0 }
    }
}