package Days.Two

import Days.Two.Solution.Game.Direction.*
import Days.Two.Solution.Game.Move
import Days.Two.Solution.Game.Version.AIM
import Days.Two.Solution.Game.Version.REGULAR
import Solvable

class Solution: Solvable<List<Move>, Pair<Int, Int>> {

    private val gameOne = Game(version = REGULAR)
    private val gameTwo = Game(version = AIM)

    data class Game(
        var state: State = State(0,0, 0),
        val version: Version
    ) {

        val product: Int
            get() = state.depth * state.position

        fun transition(move: Move) {
            state = state.update(move, version)
        }

        class State(val position: Int, val depth: Int, val aim: Int) {
            fun update(move: Move, gameVersion: Version) = when (move.direction) {
                FORWARD -> when (gameVersion) {
                    REGULAR -> State(position + move.distance, depth, aim)
                    AIM -> State(position + move.distance, depth + (aim * move.distance), aim)
                }
                UP -> when (gameVersion) {
                    REGULAR -> State(position, depth - move.distance, aim)
                    AIM -> State(position, depth, aim - move.distance)
                }
                DOWN -> when (gameVersion) {
                    REGULAR -> State(position, depth + move.distance, aim)
                    AIM -> State(position, depth, aim + move.distance)
                }
            }
        }

        data class Move(val direction: Direction, val distance: Int)
        enum class Direction { FORWARD, UP, DOWN }
        enum class Version { REGULAR, AIM }
    }

    override fun solve(input: List<Move>) = Pair(gameOne, gameTwo).run {
        input.map {
            first.transition(it)
            second.transition(it)
        }
        
        Pair(first.product, second.product)
    }
}