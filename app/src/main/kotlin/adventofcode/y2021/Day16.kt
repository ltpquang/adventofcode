package adventofcode.y2021

import adventofcode.Days
import java.util.*

class Day16: Days() {
    private fun parseHexString(input: String): Queue<String> {
        val bitQueue: Queue<String> = LinkedList()
        input.map { it.toString() }
            .map { Integer.parseInt(it, 16) }
            .map { Integer.toBinaryString(it) }
            .flatMap { it.toList().map { c -> c.toString() } }
            .forEach { bitQueue.add(it) }
        return bitQueue
    }

    private fun toSequence(queue: Queue<String>): Sequence<String> {
        return sequence {
            while (queue.isNotEmpty()) {
                yield(queue.remove())
            }
            yield("")
        }
    }

    private class Packet

    override fun runPartOneWith(input: String): String {
        val bitQueue = parseHexString(input.lines()[0])
        val seq = toSequence(bitQueue)


        println(bitQueue)
        print("[")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")
        print(seq.take(1).first())
        print(", ")



        return ""
    }

    override fun runPartTwoWith(input: String): String {
        return ""
    }
}