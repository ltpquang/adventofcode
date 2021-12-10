package adventofcode


fun main() {
    val todayNum = 3

    val className = String.format("adventofcode.days.day%02d.Day%02d", todayNum, todayNum)
    val today = Class.forName(className).getDeclaredConstructor().newInstance() as Days
    println(today.runPartOneWith(Input.forDate(2021, todayNum)))
    println(today.runPartTwoWith(Input.forDate(2021, todayNum)))
}
