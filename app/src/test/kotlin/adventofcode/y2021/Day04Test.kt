package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day04Test {

    @Test
    fun runPartOneWith() {
        assertEquals("63424", Day04().runPartOneWith(Input.forDate(2021, 4)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("23541", Day04().runPartTwoWith(Input.forDate(2021, 4)))
    }
}