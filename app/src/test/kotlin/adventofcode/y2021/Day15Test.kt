package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day15Test {

    @Test
    fun runPartOneWith() {
        assertEquals("595", Day15().runPartOneWith(Input.forDate(2021, 15)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("2914", Day15().runPartTwoWith(Input.forDate(2021, 15)))
    }
}