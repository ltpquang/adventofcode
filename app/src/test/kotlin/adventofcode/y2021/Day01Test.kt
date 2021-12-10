package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day01Test {

    @Test
    fun runPartOneWith() {
        assertEquals("1451", Day01().runPartOneWith(Input.forDate(2021, 1)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("1395", Day01().runPartTwoWith(Input.forDate(2021, 1)))
    }
}