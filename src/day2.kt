import kotlin.math.max
import kotlin.math.min

/**
 * Created by Quang Le on 1/31/18.
 *
 * "Whether you think you can, or you think you can't - you're right."
 * --Henry Ford--
 */

/**
 * --- Day 2: Corruption Checksum ---
 *
 * As you walk through the door, a glowing humanoid shape yells in your direction. "You there! Your state appears to be idle. Come help us repair the corruption in this spreadsheet - if we take another millisecond, we'll have to display an hourglass cursor!"
 *
 * The spreadsheet consists of rows of apparently-random numbers. To make sure the recovery process is on the right track, they need you to calculate the spreadsheet's checksum. For each row, determine the difference between the largest value and the smallest value; the checksum is the sum of all of these differences.
 *
 * For example, given the following spreadsheet:
 *
 * 5 1 9 5
 * 7 5 3
 * 2 4 6 8
 * The first row's largest and smallest values are 9 and 1, and their difference is 8.
 * The second row's largest and smallest values are 7 and 3, and their difference is 4.
 * The third row's difference is 6.
 * In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18.
 *
 * What is the checksum for the spreadsheet in your puzzle input?
 *
 * Your puzzle answer was 53978.
 *
 * --- Part Two ---
 *
 * "Great work; looks like we're on the right track after all. Here's a star for your effort." However, the program seems a little worried. Can programs be worried?
 *
 * "Based on what we're seeing, it looks like all the User wanted is some information about the evenly divisible values in the spreadsheet. Unfortunately, none of us are equipped for that kind of calculation - most of us specialize in bitwise operations."
 *
 * It sounds like the goal is to find the only two numbers in each row where one evenly divides the other - that is, where the result of the division operation is a whole number. They would like you to find those numbers on each line, divide them, and add up each line's result.
 *
 * For example, given the following spreadsheet:
 *
 * 5 9 2 8
 * 9 4 7 3
 * 3 8 6 5
 * In the first row, the only two numbers that evenly divide are 8 and 2; the result of this division is 4.
 * In the second row, the two numbers are 9 and 3; the result is 3.
 * In the third row, the result is 2.
 * In this example, the sum of the results would be 4 + 3 + 2 = 9.
 *
 * What is the sum of each row's result in your puzzle input?
 *
 * Your puzzle answer was 314.
 */
val DAY_2_INPUT = listOf(
    listOf(1919, 2959, 82, 507, 3219, 239, 3494, 1440, 3107, 259, 3544, 683, 207, 562, 276, 2963),
    listOf(587, 878, 229, 2465, 2575, 1367, 2017, 154, 152, 157, 2420, 2480, 138, 2512, 2605, 876),
    listOf(744, 6916, 1853, 1044, 2831, 4797, 213, 4874, 187, 6051, 6086, 7768, 5571, 6203, 247, 285),
    listOf(1210, 1207, 1130, 116, 1141, 563, 1056, 155, 227, 1085, 697, 735, 192, 1236, 1065, 156),
    listOf(682, 883, 187, 307, 269, 673, 290, 693, 199, 132, 505, 206, 231, 200, 760, 612),
    listOf(1520, 95, 1664, 1256, 685, 1446, 253, 88, 92, 313, 754, 1402, 734, 716, 342, 107),
    listOf(146, 1169, 159, 3045, 163, 3192, 1543, 312, 161, 3504, 3346, 3231, 771, 3430, 3355, 3537),
    listOf(177, 2129, 3507, 3635, 2588, 3735, 3130, 980, 324, 266, 1130, 3753, 175, 229, 517, 3893),
    listOf(4532, 164, 191, 5169, 4960, 3349, 3784, 3130, 5348, 5036, 2110, 151, 5356, 193, 1380, 3580),
    listOf(2544, 3199, 3284, 3009, 3400, 953, 3344, 3513, 102, 1532, 161, 143, 2172, 2845, 136, 2092),
    listOf(194, 5189, 3610, 4019, 210, 256, 5178, 4485, 5815, 5329, 5457, 248, 5204, 4863, 5880, 3754),
    listOf(3140, 4431, 4534, 4782, 3043, 209, 216, 5209, 174, 161, 3313, 5046, 1160, 160, 4036, 111),
    listOf(2533, 140, 4383, 1581, 139, 141, 2151, 2104, 2753, 4524, 4712, 866, 3338, 2189, 116, 4677),
    listOf(1240, 45, 254, 1008, 1186, 306, 633, 1232, 1457, 808, 248, 1166, 775, 1418, 1175, 287),
    listOf(851, 132, 939, 1563, 539, 1351, 1147, 117, 1484, 100, 123, 490, 152, 798, 1476, 543),
    listOf(1158, 2832, 697, 113, 121, 397, 1508, 118, 2181, 2122, 809, 2917, 134, 2824, 3154, 2791)
)

fun day2a(): Int {
  return DAY_2_INPUT.map {
    it.max()!! - it.min()!!
  }.sum()
}

fun day2b(): Int {
  return DAY_2_INPUT.map {
    val pair = evenlyDivisible(it)
    if (pair.first != -1)
      quotient(max(pair.first, pair.second), min(pair.first, pair.second))
    else
      0
  }.sum()
}

fun evenlyDivisible(input: List<Int>): Pair<Int, Int> {
  for ((i, valuei) in input.withIndex()) {
    for ((j, valuej) in input.withIndex()) {
      if (i != j && divisible(valuei, valuej)) {
        return Pair(valuei, valuej)
      }
    }
  }
  return Pair(-1,-1)
}

fun divisible(first: Int, second: Int): Boolean {
  return first % second == 0 || second % first == 0
}

fun quotient(devidend: Int, divisor: Int): Int {
  return devidend / divisor
}