package Days.One
import Solvable
import java.lang.Exception

class Solution: Solvable<List<Int>, Pair<Int, Int>> {

    data class Accumulator(val previous: Int, val total: Int)

    override fun solve(input: List<Int>): Pair<Int, Int> {

        // Part 1
        val totalIncreases = input.fold(Accumulator(Int.MAX_VALUE, 0)) { acc, next ->
            Accumulator(
                previous = next,
                total = if (next > acc.previous) acc.total + 1 else acc.total
            )
        }.total

        // Part 2
        val totalSlidingWindowIncreases = input.foldIndexed(Accumulator(Int.MAX_VALUE, 0)) { index, acc, _ ->
            val windowSum =  try {
                input.subList(index, index + 3)
            } catch(e: Exception) {
                listOf()
            }.sum()

            Accumulator(
                previous = windowSum,
                total = if (windowSum > acc.previous) acc.total + 1 else acc.total
            )
        }.total

        return Pair(totalIncreases, totalSlidingWindowIncreases)
    }
}