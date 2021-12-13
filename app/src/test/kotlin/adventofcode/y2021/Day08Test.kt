package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day08Test {

    @Test
    fun runPartOneWith() {
        assertEquals("367", Day08().runPartOneWith(Input.forDate(2021, 8)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("974512", Day08().runPartTwoWith(Input.forDate(2021, 8)))
    }
}