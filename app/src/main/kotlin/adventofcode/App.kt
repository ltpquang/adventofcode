package adventofcode

import adventofcode.y2021.Day04


fun main() {
    val todayNum = 4
    val today = Day04()
    println(today.runPartOneWith(Input.forDate(2021, todayNum)))
    println(today.runPartTwoWith(Input.forDate(2021, todayNum)))
}
