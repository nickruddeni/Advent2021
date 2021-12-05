
import Days.Four.Solution.Board
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

    // DAY FOUR
    val dayFourInput = Days.Four.input.lines()

    val boardLists = mutableListOf<MutableList<String>>()
    dayFourInput.drop(1).forEach { line ->
        if (line.isBlank())
            boardLists.add(mutableListOf())
        else {
            boardLists
                .last()
                .addAll(line
                    .split(" ")
                    .filter { it.isNotBlank() }
                )
        }
    }

    val dayFourResult = Days.Four.Solution().solve(
        Pair(
            dayFourInput.first().split(",").map { it.toInt() },
            boardLists.map { Board(size = 5, values = it.map { it.toInt() }) }
        )
    )
}