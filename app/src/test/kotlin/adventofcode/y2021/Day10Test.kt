package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day10Test {

    @Test
    fun runPartOneWith() {
        assertEquals("464991", Day10().runPartOneWith(Input.forDate(2021, 10)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("3662008566", Day10().runPartTwoWith(Input.forDate(2021, 10)))
    }
}