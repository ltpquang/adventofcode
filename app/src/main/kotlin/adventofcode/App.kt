package adventofcode

import adventofcode.y2021.Day08

fun main() {
    val todayNum = 8
    val today = Day08()
    println(today.runPartOneWith(Input.forDate(2021, todayNum)))
    println(today.runPartTwoWith(Input.forDate(2021, todayNum)))
}
