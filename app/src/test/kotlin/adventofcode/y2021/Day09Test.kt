package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day09Test {

    @Test
    fun runPartOneWith() {
        assertEquals("417", Day09().runPartOneWith(Input.forDate(2021, 9)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("1148965", Day09().runPartTwoWith(Input.forDate(2021, 9)))
    }
}