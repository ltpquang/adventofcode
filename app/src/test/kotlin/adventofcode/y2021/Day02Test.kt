package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day02Test {

    @Test
    fun runPartOneWith() {
        assertEquals("2073315", Day02().runPartOneWith(Input.forDate(2021, 2)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("1840311528", Day02().runPartTwoWith(Input.forDate(2021, 2)))
    }
}