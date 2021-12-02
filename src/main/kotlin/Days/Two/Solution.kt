package Days.Two

import Days.Two.Solution.Game.Direction.*
import Days.Two.Solution.Game.Move
import Solvable

class Solution(
    private val game: Game = Game()
): Solvable<List<Move>, Int> {

    data class Game(var state: State = State(0,0)) {

        val product: Int
            get() = state.depth * state.position

        fun transition(move: Move) {
            state = state.update(move)
        }

        class State(var position: Int, var depth: Int) {
            fun update(move: Move) = when (move.direction) {
                FORWARD -> State(position + move.distance, depth)
                UP -> State(position, depth - move.distance)
                DOWN -> State(position, depth + move.distance)
            }
        }

        data class Move(val direction: Direction, val distance: Int)
        enum class Direction {
            FORWARD,
            UP,
            DOWN
        }
    }

    override fun solve(input: List<Move>) = game.run {
        input.map(::transition)
        product
    }
}