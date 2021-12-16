package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day12Test {

    @Test
    fun runPartOneWith() {
        assertEquals("5874", Day12().runPartOneWith(Input.forDate(2021, 12)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("153592", Day12().runPartTwoWith(Input.forDate(2021, 12)))
    }
}