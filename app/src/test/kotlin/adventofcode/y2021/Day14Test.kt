package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day14Test {

    @Test
    fun runPartOneWith() {
        assertEquals("4244", Day14().runPartOneWith(Input.forDate(2021, 14)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("4807056953866", Day14().runPartTwoWith(Input.forDate(2021, 14)))
    }
}