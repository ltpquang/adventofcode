package adventofcode.matrix

typealias Location = Pair<Int, Int>

fun Location.stringify(): String {
    return "($first,$second)"
}

typealias IntMap = Array<Array<Int>>

operator fun IntMap.get(loc: Location): Int {
    return get(loc.row())[loc.col()]
}

operator fun IntMap.set(loc: Location, value: Int) {
    this[loc.row()][loc.col()] = value
}


typealias BooleanMap = Array<Array<Boolean>>

operator fun BooleanMap.get(loc: Location): Boolean {
    return get(loc.row())[loc.col()]
}

operator fun BooleanMap.set(loc: Location, value: Boolean) {
    this[loc.row()][loc.col()] = value
}

fun intMap(mapSize: Pair<Int, Int>, default: Int): IntMap {
    return Array(mapSize.first) {
        Array(mapSize.second) {
            default
        }
    }
}

fun generateBooleanMap(mapSize: Pair<Int, Int>): BooleanMap {
    return Array(mapSize.first) {
        Array(mapSize.second) {
            false
        }
    }
}

fun IntMap.stringify(sep: String): String {
    return this.joinToString("\n") { row ->
        row.joinToString(sep) { col -> col.toString() }
    }
}

fun BooleanMap.countTrue(): Int {
    return flatMap { it.asList() }.count { it }
}

fun BooleanMap.stringify(on: Char, off: Char, sep: String): String {
    return this.joinToString("\n") { row ->
        row.map { col -> if (col) on else off }.joinToString(sep)
    }
}

fun getSize(map: Array<Array<Int>>): Pair<Int, Int> {
    return Pair(map.size, map[0].size)
}

fun Pair<Int, Int>.outOfBound(size: Pair<Int, Int>): Boolean {
    return first < 0 || second < 0 || first == size.first || second == size.second
}

fun Pair<Int, Int>.row(): Int {
    return first
}

fun Pair<Int, Int>.col(): Int {
    return second
}

fun Pair<Int, Int>.top(): Pair<Int, Int> {
    return Pair(first - 1, second)
}

fun Pair<Int, Int>.bottom(): Pair<Int, Int> {
    return Pair(first + 1, second)
}

fun Pair<Int, Int>.left(): Pair<Int, Int> {
    return Pair(first, second - 1)
}

fun Pair<Int, Int>.right(): Pair<Int, Int> {
    return Pair(first, second + 1)
}



