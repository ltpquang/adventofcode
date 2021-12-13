package adventofcode

import adventofcode.y2021.Day07

fun main() {
    val todayNum = 7
    val today = Day07()
    println(today.runPartOneWith(Input.forDate(2021, todayNum)))
    println(today.runPartTwoWith(Input.forDate(2021, todayNum)))
}
