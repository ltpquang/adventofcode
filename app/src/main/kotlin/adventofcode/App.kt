package adventofcode

import adventofcode.y2021.Day09

fun main() {
    val todayNum = 9
    val today = Day09()
    println(today.runPartOneWith(Input.forDate(2021, todayNum)))
    println(today.runPartTwoWith(Input.forDate(2021, todayNum)))
}
