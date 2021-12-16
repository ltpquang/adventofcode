package adventofcode.y2021

import adventofcode.Input
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day13Test {

    @Test
    fun runPartOneWith() {
        assertEquals("655", Day13().runPartOneWith(Input.forDate(2021, 13)))
    }

    @Test
    fun runPartTwoWith() {
        assertEquals(
            """
                ..##.###..####..##..#..#..##..#..#.###.
                ...#.#..#....#.#..#.#..#.#..#.#..#.#..#
                ...#.#..#...#..#....#..#.#..#.#..#.#..#
                ...#.###...#...#....#..#.####.#..#.###.
                #..#.#....#....#..#.#..#.#..#.#..#.#.#.
                .##..#....####..##...##..#..#..##..#..#
            """.trimIndent(),
            Day13().runPartTwoWith(Input.forDate(2021, 13))
        )
    }
}