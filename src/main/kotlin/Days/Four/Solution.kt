package Days.Four

import Solvable

typealias Input = Pair<List<Int>, List<Solution.Board>>

class Solution: Solvable<Input, Pair<Int, Int>> {

    class Board(val size: Int = 5, val values: List<Int>) {

        data class Index(val row: Int, val column: Int)

        // Board values
        var rows = mutableListOf<MutableList<Int>>()
        var rowStates = MutableList(size) { MutableList(5) { false } }
        var columns = MutableList(size) { mutableListOf<Int>() }
        var columnStates = MutableList(size) { MutableList(5) { false } }

        private val gameWon: Boolean
            get() = rowStates.any { it.all { it } } || columnStates.any { it.all { it } }

        init {
            // Build Rows
            for (index in values.indices step size) {
                rows.add(values.subList(index, index + size).toMutableList())
            }

            // Build Columns from rows
            (1..size).forEachIndexed { index, _ ->
                rows.forEach {
                    columns[index].add(it[index])
                }
            }
        }

        // Returns if the play resulted in a win for the board.
        fun play(value: Int): Boolean {
            val index = find(value) ?: return false
            rowStates[index.row][index.column] = true
            columnStates[index.column][index.row] = true
            return gameWon
        }

        // Finds a value in the board and returns its index
        private fun find(value: Int): Index? {
            var valueIndex: Index? = null

            rows.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { columnIndex, item ->
                    if (item == value) valueIndex = Index(rowIndex, columnIndex)
                }
            }

            return valueIndex
        }
    }

    override fun solve(input: Input): Pair<Int, Int> {
        var winningBoard: Board? = null
        var winningPlay: Int? = null

        var losingBoard: Board? = null
        var losingPlay: Int? = null

        var quickestPlay = Int.MAX_VALUE
        var slowestPlay = Int.MIN_VALUE

        // Play all inputs for all boards
        input.second.forEach gameLoop@ { board ->
            var plays = 0

            input.first.forEach inputLoop@ {
                plays++

                if (!board.play(it)) return@inputLoop

                if (plays < quickestPlay) {
                    winningBoard = board
                    winningPlay = it
                    quickestPlay = plays
                } else if (plays > slowestPlay) {
                    losingBoard = board
                    losingPlay = it
                    slowestPlay = plays
                }

                return@gameLoop
            }
        }

        val winner = calculateResult(winningBoard!!, winningPlay!!)
        val loser = calculateResult(losingBoard!!, losingPlay!!)

        return Pair(winner, loser)
    }

    private fun calculateResult(board: Board, finalPlay: Int): Int {
        val finalStates = board.rowStates.flatten()
        val values = board.rows.flatten()

        val indexes = finalStates.mapIndexedNotNull { index, state ->
            if (!state) index else null
        }

        val unmarked = values.filterIndexed { index, _ ->
            indexes.contains(index)
        }

        return unmarked.sum() * finalPlay
    }
}