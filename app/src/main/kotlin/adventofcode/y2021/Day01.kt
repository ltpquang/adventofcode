package adventofcode.y2021

import adventofcode.Days

class Day01: Days() {
    override fun runPartOneWith(input: String): String {
        val nums = input
            .reader()
            .readLines()
            .map { it.toInt() }

        val result = countIncrement(nums)

        return result.toString()
    }

    override fun runPartTwoWith(input: String): String {
        val nums0 = input
            .reader()
            .readLines()
            .map { it.toInt() }

        val nums1 = nums0.toMutableList()
        nums1.add(0)
        nums1.removeAt(0)

        val nums2 = nums1.toMutableList()
        nums2.add(0)
        nums2.removeAt(0)

        val windowOfTwo = nums0.zip(nums1).map { it.first + it.second }
        val windowOfThree = windowOfTwo.zip(nums2).map { it.first + it.second }

        val result = countIncrement(windowOfThree)

        return result.toString()
    }

    private fun countIncrement(input: List<Int>): Int {
        val numsNext = input.toMutableList()
        numsNext.add(0)
        numsNext.removeAt(0)

        return input.zip(numsNext).count { (it, nextIt) -> it < nextIt }
    }
}