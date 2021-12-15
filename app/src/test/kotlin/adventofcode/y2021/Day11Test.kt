package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day11Test {

    @Test
    fun runPartOneWith() {
        assertEquals("1694", Day11().runPartOneWith(Input.forDate(2021, 11)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals("346", Day11().runPartTwoWith(Input.forDate(2021, 11)))
    }
}