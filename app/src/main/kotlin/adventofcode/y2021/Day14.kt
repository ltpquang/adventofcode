package adventofcode.y2021

import adventofcode.Days

class Day14 : Days() {
    private fun parseInput(input: String): Pair<String, MutableMap<String, String>> {
        val template = input.lines().take(1)[0]
        val insertions = mutableMapOf<String, String>()
        input.lines().filter { it.contains("->") }
            .map { it.split(" -> ") }
            .forEach { insertions[it[0]] = it[1] }
        return Pair(template, insertions)
    }

    override fun runPartOneWith(input: String): String {
        val data = parseInput(input)
        var template = data.first
        val insertions = data.second

        repeat(10) {
            template = template.split("").filter { it.isNotEmpty() }
                .mapIndexed { index, s ->
                    if (index == template.length - 1) {
                        s
                    } else if (insertions.containsKey(template.substring(index, index + 2))) {
                        s + insertions[template.substring(index, index + 2)]
                    } else {
                        s
                    }
                }
                .joinToString("")
        }

        val counts = template
            .split("")
            .filter { it.isNotEmpty() }
            .groupingBy { it }
            .eachCount()
            .map { it.value }
            .sortedDescending()

        return (counts.first() - counts.last()).toString()
    }

    private val cache = mutableMapOf<String, Map<String, Long>>()

    private fun cacheKey(pair: String, i: Int): String = "$pair-$i"

    private fun mergeCountMap(lhs: Map<String, Long>, rhs: Map<String, Long>): Map<String, Long> {
        return (lhs.keys + rhs.keys).associateWith {
            lhs.getOrDefault(it, 0) + rhs.getOrDefault(it, 0)
        }.toMap()
    }

    private fun getCountMap(
        pair: String,
        i: Int,
        insertions: Map<String, String>
    ): Map<String, Long> {
        if (i == 0) {
            val result = mapOf(Pair(pair.first().toString(), 1L))
            cache[cacheKey(pair, i)] = result
            return result
        }
        if (cache.containsKey(cacheKey(pair, i))) {
            return cache[cacheKey(pair, i)]!!.toMap()
        }
        if (!insertions.containsKey(pair)) {
            return getCountMap(pair, i - 1, insertions)
        }

        val inserted = insertions[pair]!!
        val result = mergeCountMap(
            getCountMap("${pair.first()}$inserted", i - 1, insertions),
            getCountMap("$inserted${pair.last()}", i - 1, insertions)
        )
        cache[cacheKey(pair, i)] = result
        return result
    }

    override fun runPartTwoWith(input: String): String {
        val data = parseInput(input)
        val template = data.first
        val insertions = data.second

        var resultCountMap = mapOf(Pair(template.last().toString(), 1L))

        val pairs = List(template.length - 1) { index ->
            if (index == template.length - 1) {
                ""
            } else {
                template.substring(index, index + 2)
            }
        }
            .filter { it.isNotEmpty() }
            .toList()
            .forEach { pair ->
                resultCountMap = mergeCountMap(resultCountMap, getCountMap(pair, 40, insertions))
            }


        val counts = resultCountMap.map { it.value }.sortedDescending()

        return (counts.first() - counts.last()).toString()
    }
}