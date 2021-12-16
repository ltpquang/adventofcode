package adventofcode

import adventofcode.y2021.Day12


fun main() {
    val todayNum = 12
    val today = Day12()
    println(today.runPartOneWith(Input.forDate(2021, todayNum)))
    println(today.runPartTwoWith(Input.forDate(2021, todayNum)))
}
