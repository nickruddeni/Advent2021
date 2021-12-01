import java.lang.Exception
import kotlin.Int.Companion.MAX_VALUE

fun main() {

    data class Accumulator(val previous: Int, val total: Int)

    // Input for both parts of the challenge
    val puzzleInput = puzzleInput
        .lines()
        .map { it.toInt() }
    
    // Part 1
    val totalIncreases = puzzleInput.fold(Accumulator(MAX_VALUE, 0)) { acc, next ->
        Accumulator(
            previous = next,
            total = if (next > acc.previous) acc.total + 1 else acc.total
        )
    }.total

    // Part 2
    val totalSlidingWindowIncreases = puzzleInput.foldIndexed(Accumulator(MAX_VALUE, 0)) { index, acc, _ ->
        val windowSum =  try {
            puzzleInput.subList(index, index + 3)
        } catch(e: Exception) {
            listOf()
        }.sum()

        Accumulator(
            previous = windowSum,
            total = if (windowSum > acc.previous) acc.total + 1 else acc.total
        )
    }.total
}