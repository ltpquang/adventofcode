import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

/**
 * Created by Quang Le on 1/31/18.
 *
 * "Whether you think you can, or you think you can't - you're right."
 * --Henry Ford--
 */

/**
 * --- Day 3: Spiral Memory ---
 *
 * You come across an experimental new kind of memory stored on an infinite two-dimensional grid.
 *
 * Each square on the grid is allocated in a spiral pattern starting at a location marked 1 and then counting up while spiraling outward. For example, the first few squares are allocated like this:
 *
 * 17  16  15  14  13
 * 18   5   4   3  12
 * 19   6   1   2  11
 * 20   7   8   9  10
 * 21  22  23---> ...
 * While this is very space-efficient (no squares are skipped), requested data must be carried back to square 1 (the location of the only access port for this memory system) by programs that can only move up, down, left, or right. They always take the shortest path: the Manhattan Distance between the location of the data and square 1.
 *
 * For example:
 *
 * Data from square 1 is carried 0 steps, since it's at the access port.
 * Data from square 12 is carried 3 steps, such as: down, left, left.
 * Data from square 23 is carried only 2 steps: up twice.
 * Data from square 1024 must be carried 31 steps.
 * How many steps are required to carry the data from the square identified in your puzzle input all the way to the access port?
 *
 * Your puzzle answer was 438.
 *
 * --- Part Two ---
 *
 * As a stress test on the system, the programs here clear the grid and then store the value 1 in square 1. Then, in the same allocation order as shown above, they store the sum of the values in all adjacent squares, including diagonals.
 *
 * So, the first few squares' values are chosen as follows:
 *
 * Square 1 starts with the value 1.
 * Square 2 has only one adjacent filled square (with value 1), so it also stores 1.
 * Square 3 has both of the above squares as neighbors and stores the sum of their values, 2.
 * Square 4 has all three of the aforementioned squares as neighbors and stores the sum of their values, 4.
 * Square 5 only has the first and fourth squares as neighbors, so it gets the value 5.
 * Once a square is written, its value does not change. Therefore, the first few squares would receive the following values:
 *
 * 147  142  133  122   59
 * 304    5    4    2   57
 * 330   10    1    1   54
 * 351   11   23   25   26
 * 362  747  806--->   ...
 * What is the first value written that is larger than your puzzle input?
 *
 * Your puzzle answer was 266330.
 */

val DAY_3_INPUT = 265149

fun day3a(): Int {
  val input = DAY_3_INPUT
  val sqrt = ceil(sqrt(input * 1.0)).toInt()
  val side = if (sqrt % 2 == 0) sqrt + 1 else sqrt

  val bottomRight = (side * side)
  val bottomLeft = bottomRight - (side - 1)
  val topLeft = bottomLeft - (side - 1)
  val topRight = topLeft - (side - 1)

  val bottom = bottomRight - (side / 2)
  val left = bottomLeft - (side / 2)
  val top = topLeft - (side / 2)
  val right = topRight - (side / 2)

  val distanceCenterToRoot = side / 2

  return when (input) {
    in bottomLeft..bottomRight -> abs(input - bottom) + distanceCenterToRoot
    in topLeft..bottomLeft -> abs(input - left) + distanceCenterToRoot
    in topRight..topLeft -> abs(input - top) + distanceCenterToRoot
    else -> abs(input - right) + distanceCenterToRoot
  }
}

enum class DIRECTION { UP, DOWN, LEFT, RIGHT }
class Point(val x: Int, val y: Int) {
  fun move(direction: DIRECTION): Point {
    return when (direction) {
      DIRECTION.UP -> Point(x, y + 1)
      DIRECTION.DOWN -> Point(x, y - 1)
      DIRECTION.LEFT -> Point(x - 1, y)
      DIRECTION.RIGHT -> Point(x + 1, y)
    }
  }
}

class Data(var data: MutableMap<Int, MutableMap<Int, Int>>) {
  fun get(position: Point): Int {
    if (position.x == 0 && position.y == 0) {
      return 1
    }

    val jMap = data[position.x] ?: return 0
    return jMap[position.y] ?: return 0
  }

  fun put(position: Point, value: Int) {
    if (!data.containsKey(position.x)) {
      data[position.x] = HashMap()
    }

    val jMap = data[position.x]
    jMap!![position.y] = value
  }

  fun compute(position: Point): Int {
    return if (position.x == 0 && position.y == 0) {
      1
    } else {
      listOf(
          get(position.move(DIRECTION.UP)),
          get(position.move(DIRECTION.DOWN)),
          get(position.move(DIRECTION.LEFT)),
          get(position.move(DIRECTION.RIGHT)),
          get(position.move(DIRECTION.UP).move(DIRECTION.LEFT)),
          get(position.move(DIRECTION.UP).move(DIRECTION.RIGHT)),
          get(position.move(DIRECTION.DOWN).move(DIRECTION.LEFT)),
          get(position.move(DIRECTION.DOWN).move(DIRECTION.RIGHT)))
          .sum()
    }
  }
}

fun day3b(): Int {
  val data = Data(HashMap())
  var currentPosition = Point(0, 0)
  var currentDirection = DIRECTION.RIGHT
  while (true) {
    val value = data.compute(currentPosition)
    if (value > DAY_3_INPUT) {
      return value
    } else {
      data.put(currentPosition, value)
      currentPosition = currentPosition.move(currentDirection)
      currentDirection = navigate(currentPosition, currentDirection, data)
    }
  }
}


fun navigate(point: Point, direction: DIRECTION, data: Data): DIRECTION {
  return when {
    direction == DIRECTION.UP && data.get(point.move(DIRECTION.LEFT)) == 0 -> DIRECTION.LEFT
    direction == DIRECTION.LEFT && data.get(point.move(DIRECTION.DOWN)) == 0 -> DIRECTION.DOWN
    direction == DIRECTION.DOWN && data.get(point.move(DIRECTION.RIGHT)) == 0 -> DIRECTION.RIGHT
    direction == DIRECTION.RIGHT && data.get(point.move(DIRECTION.UP)) == 0 -> DIRECTION.UP
    else -> direction
  }
}



