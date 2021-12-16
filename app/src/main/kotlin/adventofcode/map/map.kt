package adventofcode.map

typealias ListStringMap = MutableMap<String, MutableList<String>>

fun listStringMap(): ListStringMap {
    return HashMap()
}

fun ListStringMap.add(key: String, value: String) {
    if (!containsKey(key)) {
        this[key] = mutableListOf()
    }
    this[key]?.add(value)
}

