package Days.Three

import Days.Three.Solution.Commonality.LEAST
import Days.Three.Solution.Commonality.MOST
import Solvable

class Solution: Solvable<List<String>, Pair<Int, Int>> {

    enum class Commonality { MOST, LEAST }

    override fun solve(input: List<String>): Pair<Int, Int> {

        // PART ONE
        val bitCount = input.first().count()
        val splitLists = mutableListOf<MutableList<String>>()

        var gamma = ""
        var epsilon = ""

        (1..bitCount).map { splitLists.add(mutableListOf()) }

        // Build the Matrix
        input.forEach {
            for (index in 0 until bitCount) {
                splitLists[index].add(it[index].toString())
            }
        }

        // Do the solving
        for (index in 0 until bitCount) {
            gamma = gamma.plus(occuring(MOST, input, index))
            epsilon = epsilon.plus(occuring(LEAST, input, index))
        }

        val consumption = Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)

        // PART 2
        fun filter(commonality: Commonality, input: MutableList<String>): List<String> {
            var total = input

            for (index in 0 until bitCount) {
                val occuring = occuring(commonality, total, index)
                total = total.filter { it[index].toString() == occuring }.toMutableList()

                if (total.size == 1) break
            }

            return total
        }

        val oxygenResult = filter(MOST, input.toMutableList())
        val co2Result = filter(LEAST, input.toMutableList())

        val lifeSupportRating =
            Integer.parseInt(oxygenResult.first(), 2) * Integer.parseInt(co2Result.first(), 2)

        return Pair(consumption, lifeSupportRating)
    }

    // Returns the most or least common character in a list at a given index
    private fun occuring(commonality: Commonality, list: List<String>, index: Int): String {
        val grouped = list
            .map { it[index].toString() }
            .groupBy { it }

        val zeroes = grouped["0"]?.size ?: 0
        val ones = grouped["1"]?.size ?: 0

        return when (commonality) {
            MOST -> if (ones >= zeroes) "1" else "0"
            LEAST -> if (ones >= zeroes) "0" else "1"
        }
    }
}