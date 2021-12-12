package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day06Test {

    @Test
    fun runPartOneWith() {
        assertEquals("350605", Day06().runPartOneWith(Input.forDate(2021, 6)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("1592778185024", Day06().runPartTwoWith(Input.forDate(2021, 6)))
    }
}