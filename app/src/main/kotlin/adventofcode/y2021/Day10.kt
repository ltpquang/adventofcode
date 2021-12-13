package adventofcode.y2021

import adventofcode.Days
import java.util.*

class Day10 : Days() {
    private val scoreInvalid = hashMapOf(
        Pair(')', 3),
        Pair(']', 57),
        Pair('}', 1197),
        Pair('>', 25137),
    )

    private val scoreComplete = hashMapOf(
        Pair(')', 1),
        Pair(']', 2),
        Pair('}', 3),
        Pair('>', 4),
    )

    private fun Char.isOpen(): Boolean {
        return this in arrayOf('(', '[', '{', '<')
    }

    private fun Char.getClose(): Char {
        return when {
            this == '(' -> ')'
            this == '[' -> ']'
            this == '{' -> '}'
            this == '<' -> '>'
            else -> '|'
        }
    }

    private fun Char.isCloseFor(open: Char): Boolean {
        if (!open.isOpen()) {
            return false
        }
        return (open == '(' && this == ')') ||
                (open == '[' && this == ']') ||
                (open == '{' && this == '}') ||
                (open == '<' && this == '>')
    }

    private fun findIllegal(chars: CharArray): Char {
        val stack = Stack<Char>()

        for (c in chars) {
            if (c.isOpen()) {
                stack.push(c)
                continue
            }

            if (stack.isEmpty()) {
                return '|'
            }

            val peak = stack.peek()

            if (c.isCloseFor(peak)) {
                stack.pop()
                continue
            }

            return c
        }

        return '|'
    }

    override fun runPartOneWith(input: String): String {
        return input.lines().filter { it.isNotEmpty() }
            .map { findIllegal(it.toCharArray()) }
            .filter { it != '|' }
            .sumOf { scoreInvalid.getOrDefault(it, 0) }
            .toString()
    }

    private fun complete(chars: CharArray): CharArray {
        val stack = Stack<Char>()

        for (c in chars) {
            if (c.isOpen()) {
                stack.push(c)
                continue
            }

            if (stack.isEmpty()) {
                return charArrayOf()
            }

            val peak = stack.peek()

            if (c.isCloseFor(peak)) {
                stack.pop()
                continue
            }

            return charArrayOf()
        }

        return stack.asReversed()
            .filter { it.isOpen() }
            .map { it.getClose() }
            .toCharArray()
    }

    override fun runPartTwoWith(input: String): String {
        val resultList = input.lines()
            .filter { it.isNotEmpty() }
            .map { complete(it.toCharArray()) }
            .filter { it.isNotEmpty() }
            .map {
                it.map { c ->
                    scoreComplete.getOrDefault(c, 0).toLong()
                }.fold(0L) { result, element -> result * 5 + element }
            }
            .sorted()
        return resultList[resultList.size / 2].toString()
    }
}
