package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day07Test {

    @Test
    fun runPartOneWith() {
        assertEquals("354129", Day07().runPartOneWith(Input.forDate(2021, 7)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("98905973", Day07().runPartTwoWith(Input.forDate(2021, 7)))
    }
}