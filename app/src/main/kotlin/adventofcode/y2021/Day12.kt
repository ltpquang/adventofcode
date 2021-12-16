package adventofcode.y2021

import adventofcode.Days
import adventofcode.map.ListStringMap
import adventofcode.map.add
import adventofcode.map.listStringMap
import java.util.*

class Day12 : Days() {
    private fun parseInput(input: String): ListStringMap {
        val result = listStringMap()
        input.lines().filter { it.isNotEmpty() }
            .map { it.split("-") }
            .forEach {
                result.add(it[0], it[1])
                result.add(it[1], it[0])
            }
        return result
    }

    private fun traverse(vertex: String, path: Stack<String>, map: ListStringMap): Int {
        if (vertex == "end") {
            return 1
        }
        if (vertex == vertex.lowercase() && path.contains(vertex)) {
            return 0
        }

        val connectedVertices = map[vertex]

        var result = 0
        for (next in connectedVertices!!) {
            path.push(vertex)
            result += traverse(next, path, map)
            path.pop()
        }

        return result
    }

    private fun Stack<String>.canGoToLowercase(vertex: String): Boolean {
        val keyWithRepeatCount = this.filter { it == it.lowercase() }
            .groupingBy { it }
            .eachCount()

        val numberOfRepeatedLowercase = keyWithRepeatCount.filter { it.value > 1 }.count()
        if (numberOfRepeatedLowercase == 0) {
            return true
        }
        if (numberOfRepeatedLowercase > 1) {
            return false
        }

        if (keyWithRepeatCount.containsKey(vertex)) {
            return false
        }

        return true
    }

    private fun traversePart2(vertex: String, path: Stack<String>, map: ListStringMap): Int {
        if (vertex == "end") {
            return 1
        }
        if (vertex == "start" && path.contains("start")) {
            return 0
        }
        if (vertex == vertex.lowercase() && !path.canGoToLowercase(vertex)) {
            return 0
        }

        val connectedVertices = map[vertex]

        var result = 0
        for (next in connectedVertices!!) {
            path.push(vertex)
            result += traversePart2(next, path, map)
            path.pop()
        }

        return result
    }

    override fun runPartOneWith(input: String): String {
        val map = parseInput(input)
        val result = traverse("start", Stack(), map)
        return result.toString()
    }

    override fun runPartTwoWith(input: String): String {
        val map = parseInput(input)
        val result = traversePart2("start", Stack(), map)
        return result.toString()
    }
}