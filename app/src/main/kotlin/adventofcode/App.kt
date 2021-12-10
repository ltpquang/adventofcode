package adventofcode

import adventofcode.days.Days

fun main() {
    val todayNum = 2

    val className = String.format("adventofcode.days.day%02d.Day%02d", todayNum, todayNum)
    val today = Class.forName(className).getDeclaredConstructor().newInstance() as Days
    println(today.runPartOneWith(Input.getFor(2021, todayNum)))
    println(today.runPartTwoWith(Input.getFor(2021, todayNum)))
}
