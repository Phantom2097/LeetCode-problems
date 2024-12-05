package NotSeasons

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (N, K, t) = reader.readLine().split(" ").map { it.toInt() }
    val heights = mutableListOf<Pair<Int, Int>>()

    var best = 0
    for (i in 0 ..< N) {
        val height = reader.readLine()!!.toInt()
        heights.add(Pair(height, i + 1))
    }

    heights.sortBy { it.first }

    val targetIndex = heights.indexOfFirst { it.second == t }

    var minDifference = Int.MAX_VALUE

    for (i in 0 .. (N - K)) {
        if (i <= targetIndex && targetIndex < i + K) {
            val currentDifference = heights[i + K - 1].first - heights[i].first
            minDifference = minOf(minDifference, currentDifference)
        }
    }

    writer.write("$minDifference")

    reader.close()
    writer.close()
}
