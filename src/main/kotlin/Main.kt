
import Days.Two.Solution.Game.Direction
import Days.Two.Solution.Game.Move

fun main() {

    val dayOneResult = Days.One.Solution().solve(
        Days.One.input
            .lines()
            .map { it.toInt() }
    )

    val dayTwoResult = Days.Two.Solution().solve(
        Days.Two.input
            .lines()
            .map {
                val moveComponents = it.split(" ")
                Move(
                    direction = Direction.valueOf(moveComponents[0].uppercase()),
                    distance = moveComponents[1].toInt()
                )
            }
    )

    val dayThreeResult = Days.Three.Solution().solve(
        Days.Three.input
            .lines()
    )
}