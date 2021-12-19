package adventofcode

import adventofcode.y2021.Day15


fun main() {
    val todayNum = 15
    val today = Day15()
    println(today.runPartOneWith(Input.forDate(2021, todayNum)))
    println(today.runPartTwoWith(Input.forDate(2021, todayNum)))
}
