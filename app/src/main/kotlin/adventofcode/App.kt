package adventofcode

import adventofcode.y2021.Day10


fun main() {
    val todayNum = 10
    val today = Day10()
    println(today.runPartOneWith(Input.forDate(2021, todayNum)))
    println(today.runPartTwoWith(Input.forDate(2021, todayNum)))
}
