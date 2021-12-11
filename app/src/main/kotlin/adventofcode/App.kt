package adventofcode

import adventofcode.y2021.Day05

fun main() {
    val todayNum = 5
    val today = Day05()
    println(today.runPartOneWith(Input.forDate(2021, todayNum)))
    println(today.runPartTwoWith(Input.forDate(2021, todayNum)))
}
