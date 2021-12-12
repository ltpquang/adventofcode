package adventofcode

import adventofcode.y2021.Day06


fun main() {
    val todayNum = 6
    val today = Day06()
    println(today.runPartOneWith(Input.forDate(2021, todayNum)))
    println(today.runPartTwoWith(Input.forDate(2021, todayNum)))
}
