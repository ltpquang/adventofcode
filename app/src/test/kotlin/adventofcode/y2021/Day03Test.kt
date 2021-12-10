package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day03Test {

    @Test
    fun runPartOneWith() {
        assertEquals("1071734", Day03().runPartOneWith(Input.forDate(2021, 3)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("6124992", Day03().runPartTwoWith(Input.forDate(2021, 3)))
    }
}