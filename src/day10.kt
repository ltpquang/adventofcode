import java.lang.StringBuilder

/**
 * Created by Quang Le on 3/7/18.
 *
 * "Whether you think you can, or you think you can't - you're right."
 * --Henry Ford--
 */

//val DAY_10_INPUT = listOf(3,4,1,5)
val DAY_10_INPUT = listOf(165,1,255,31,87,52,24,113,0,91,148,254,158,2,73,153)

class Node10(val value: Int) {
  var next: Node10? = this
}

class CircularLinkedList(size: Int) {
  var root: Node10
  var current: Node10
  init {
    root = Node10(0)
    current = root
    var last = root
    for (i in 1..size - 1) {
      val temp = Node10(i)
      last.next = temp
      temp.next = root
      last = temp
    }
    last.next = root
  }

  fun getNodeBeforeCurrent(): Node10 {
    var temp = current
    while (temp.next != current) {
      temp = temp.next!!
    }
    return temp
  }

  fun getLastNodeOfLengthFromCurrent(count: Int): Node10 {
    var temp = current
    for (i in 1..count - 1) {
      temp = temp.next!!
    }
    return temp
  }

  fun toListInt(): List<Int> {
    val result = ArrayList<Int>()
    result.add(root.value)
    var temp = root.next!!
    while (temp != root) {
      result.add(temp.value)
      temp = temp.next!!
    }
    return result
  }
}

fun day10a(): Int {
  val list = CircularLinkedList(256)
  val lengths = DAY_10_INPUT

  for ((skipSize, it) in lengths.withIndex()) {
    val lastNode = list.getLastNodeOfLengthFromCurrent(it)

    val beforeCurrent = list.getNodeBeforeCurrent()
    beforeCurrent.next = null

    var afterLast = lastNode.next
    lastNode.next = null

    var cursor = list.current

    var passingRoot = false
    var countFromCurrentUntilRoot = 0

    while (cursor.next != null) {
      if (cursor == list.root) {
        passingRoot = true
      }
      if (!passingRoot) {
        countFromCurrentUntilRoot++
      }

      val temp = cursor
      cursor = cursor.next!!
      temp.next = afterLast
      afterLast = temp
    }

    cursor.next = afterLast
    beforeCurrent.next = cursor
    list.current = cursor

    for (i in 1..(it + skipSize)) {
      list.current = list.current.next!!
    }

    if (passingRoot) {
      list.root = beforeCurrent.next!!
      for (i in 0 until countFromCurrentUntilRoot) {
        list.root = list.root.next!!
      }
    }
  }

  return list.root.value * list.root.next!!.value
}

fun day10b(): String {
  val list = CircularLinkedList(256)
  val lengths = getDay10bInput()

  println(lengths)
  (0 until lengths.size * 64).withIndex().forEach { (skipSize, value) ->
    val it = lengths[value%lengths.size]
    val lastNode = list.getLastNodeOfLengthFromCurrent(it)

    val beforeCurrent = list.getNodeBeforeCurrent()
    beforeCurrent.next = null

    var afterLast = lastNode.next
    lastNode.next = null

    var cursor = list.current

    var passingRoot = false
    var countFromCurrentUntilRoot = 0

    while (cursor.next != null) {
      if (cursor == list.root) {
        passingRoot = true
      }
      if (!passingRoot) {
        countFromCurrentUntilRoot++
      }

      val temp = cursor
      cursor = cursor.next!!
      temp.next = afterLast
      afterLast = temp
    }

    cursor.next = afterLast
    beforeCurrent.next = cursor
    list.current = cursor

    for (i in 1..(it + skipSize)) {
      list.current = list.current.next!!
    }

    if (passingRoot) {
      list.root = beforeCurrent.next!!
      for (i in 0 until countFromCurrentUntilRoot) {
        list.root = list.root.next!!
      }
    }

    println(list.toListInt())
  }

  val denseHash = IntArray(16)
  denseHash.fill(0)

  list.toListInt().withIndex().forEach { (index, value) -> denseHash[index/16] = denseHash[index/16].xor(value) }

  val builder = StringBuilder()

  denseHash.forEach { builder.append(Integer.toHexString(256.or(it)).substring(1..2)) }

  return builder.toString()
}

fun getDay10bInput(): List<Int> {

  val input = listOf(49, 54, 53, 44, 49, 44, 50, 53, 53, 44, 51, 49, 44, 56, 55, 44, 53, 50, 44, 50, 52, 44, 49, 49, 51, 44, 48, 44, 57, 49, 44, 49, 52, 56, 44, 50, 53, 52, 44, 49, 53, 56, 44, 50, 44, 55, 51, 44, 49, 53, 51)
  val magicLengths = listOf(17, 31, 73, 47, 23)

  return (input + magicLengths).toMutableList()



//  val result =
////      DAY_10_INPUT
////      .joinToString(",")
//  "165,1,255,31,87,52,24,113,0,91,148,254,158,2,73,153"
//      .map { it.toInt() }
//      .toMutableList()
//  result.addAll(listOf(17, 31, 73, 47, 23))
//  return result
}
