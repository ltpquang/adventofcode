package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day05Test {

    @Test
    fun runPartOneWith() {
        assertEquals("5124", Day05().runPartOneWith(Input.forDate(2021, 5)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("19771", Day05().runPartTwoWith(Input.forDate(2021, 5)))
    }
}