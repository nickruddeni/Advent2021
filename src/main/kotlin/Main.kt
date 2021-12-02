
import Days.Two.Solution.Game.Direction
import Days.Two.Solution.Game.Move

fun main() {

    val dayOne = Days.One.Solution()
    val dayOneResult = dayOne.solve(
        Days.One.input
            .lines()
            .map { it.toInt() }
    )

    val dayTwo = Days.Two.Solution()
    val dayTwoResult = dayTwo.solve(
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
}