package adventofcode.y2021

import adventofcode.Days
import java.util.*
import kotlin.collections.HashMap

class Day06 : Days() {
    private class Lanternfish {
        companion object {
            fun init(initTime: Byte): Lanternfish {
                val result = Lanternfish()
                result.timer = initTime
                return result
            }
        }

        var timer: Byte = 8

        fun nextDay(): Boolean {
            if (timer != 0.toByte()) {
                timer = timer.dec()
                return false
            }
            timer = 6
            return true
        }
    }

    private class Machine(children: List<Lanternfish>) {
        val children = LinkedList(children)
        fun nextDay() {
            val newBorn = children
                .map { it.nextDay() }
                .count { it }
            repeat(newBorn) {
                children.add(Lanternfish())
            }
        }
    }

    override fun runPartOneWith(input: String): String {
        val result = input.trim().split(",")
            .map { it.toInt() }.sumOf { normalCount(0, 80 - it) }
        return result.toString()
    }

    private fun normalCount(timer: Int, days: Int): Int {
        val machine = Machine(Collections.singletonList(Lanternfish.init(timer.toByte())))
        for (i in 0 until days) {
            machine.nextDay()
        }
        return machine.children.size
    }

    override fun runPartTwoWith(input: String): String {
        val result = input.trim().split(",")
            .map { it.toInt() }.sumOf { f(it, 0, 256) }
        return result.toString()
    }

    private val cache = HashMap<Long, Long>()

    private fun f(timer: Int, dayIndex: Int, finishDay: Int): Long {
        val cacheKey = timer.toLong().shl(Int.SIZE_BITS) or dayIndex.toLong()

        if (cache.containsKey(cacheKey)) {
            return cache[cacheKey]!!
        }

        if (dayIndex == finishDay) {
            cache[cacheKey] = 1
            return 1
        }
        var nextTimer = timer - 1
        if (nextTimer < 0) {
            nextTimer = 6
        }
        if (timer == 0) {
            cache[cacheKey] = f(nextTimer, dayIndex + 1, finishDay) + f(8, dayIndex + 1, finishDay)
        } else {
            cache[cacheKey] = f(nextTimer, dayIndex + 1, finishDay)
        }
        return cache[cacheKey]!!
    }
}