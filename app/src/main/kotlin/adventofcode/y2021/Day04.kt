package adventofcode.y2021

import adventofcode.Days

class Day04 : Days() {
    private class Board private constructor(
        val data: Array<Array<Int>>,
        val marked: Array<Array<Int>>
    ) {
        companion object {
            const val BOARD_SIZE = 5

            fun parse(input: List<String>): Board {
                val data = input
                    .map {
                        it.split(" ").map { numberString -> numberString.toInt() }.toTypedArray()
                    }
                    .toTypedArray()

                val mark = Array(BOARD_SIZE) { Array(BOARD_SIZE) { 0 } }
                return Board(data, mark)
            }
        }

        fun mark(number: Int) {
            data.forEachIndexed { iRow, row ->
                row.forEachIndexed { iCol, col ->
                    if (col == number) {
                        marked[iRow][iCol] = 1
                    }
                }
            }
        }

        fun win(): Boolean {
            for (i in 0 until BOARD_SIZE) {
                if (wholeColMarked(i) || wholeRowMarked(i)) {
                    return true
                }
            }
            return false
        }

        fun getScore(): Int {
            var sum = 0
            data.forEachIndexed { iRow, row ->
                row.forEachIndexed { iCol, col ->
                    if (marked[iRow][iCol] == 0) {
                        sum += col
                    }
                }
            }
            return sum
        }

        private fun wholeRowMarked(rowIndex: Int): Boolean {
            return marked[rowIndex].sum() == BOARD_SIZE
        }

        private fun wholeColMarked(colIndex: Int): Boolean {
            return marked.sumOf { it[colIndex] } == BOARD_SIZE
        }
    }

    override fun runPartOneWith(input: String): String {
        val lines = input
            .replace("  ", " ")
            .replace("\n ", "\n")
            .lines().filter { it.isNotEmpty() }.toMutableList()
        val drawnNumbers = lines.removeAt(0).split(",").map { it.toInt() }
        val boards = lines.chunked(5).map { Board.parse(it) }

        for (drawn in drawnNumbers) {
            boards.forEach { it.mark(drawn) }
            val wonBoard = boards.firstOrNull { it.win() }

            if (wonBoard != null) {
                return (wonBoard.getScore() * drawn).toString()
            }
        }

        return ""
    }

    override fun runPartTwoWith(input: String): String {
        val lines = input
            .replace("  ", " ")
            .replace("\n ", "\n")
            .lines().filter { it.isNotEmpty() }.toMutableList()
        val drawnNumbers = lines.removeAt(0).split(",").map { it.toInt() }
        val boards = lines.chunked(5).map { Board.parse(it) }.toMutableList()

        for (drawn in drawnNumbers) {
            boards.forEach { it.mark(drawn) }
            val wonBoard = boards.firstOrNull { it.win() }
            boards.firstOrNull { !it.win() }
                ?: return (wonBoard?.getScore()?.times(drawn)).toString()

            boards.removeIf { it.win() }
        }

        return ""
    }
}
