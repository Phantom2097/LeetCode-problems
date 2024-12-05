import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val n = reader.readLine().toInt()
    val matrix = Array(n) { IntArray(n) }

    for (i in 0..< n) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (j in 0..< n) {
            matrix[i][j] = row[j]
        }
    }

    val dp = Array(n) { IntArray(n) }
    val count = Array(n) { IntArray(n) }
    val pathLengths = mutableMapOf<Int, Int>()

    fun dfs(i: Int, j: Int): Int {
        if (dp[i][j] != 0) return dp[i][j]

        var maxLength = 1
        var numPaths = if (i == 0 || i == n-1 || j == 0 || j == n-1) 1 else 0

        val directions = arrayOf(
            intArrayOf(0, 1), // right
            intArrayOf(1, 0), // down
            intArrayOf(0, -1), // left
            intArrayOf(-1, 0) // up
        )

        for (dir in directions) {
            val ni = i + dir[0]
            val nj = j + dir[1]

            if (ni in 0..< n && nj in 0..< n && matrix[ni][nj] > matrix[i][j]) {
                val length = 1 + dfs(ni, nj)
                if (length > maxLength) {
                    maxLength = length
                    numPaths = count[ni][nj]
                } else if (length == maxLength) {
                    numPaths += count[ni][nj]
                }
            }
        }

        dp[i][j] = maxLength
        count[i][j] = numPaths
        return maxLength
    }

//    var maxPathLength = 0
//    var totalPaths = 0

    for (i in 0..< n) {
        for (j in 0..< n) {
            val length = dfs(i, j)
            // We only consider the path if it ends on the edge
            if (i == 0 || i == n-1 || j == 0 || j == n-1) {
                pathLengths[length] = pathLengths.getOrDefault(length, 0) + count[i][j]
            }
        }
    }
    for ((key, value) in pathLengths) {
        if (pathLengths[key] == 0) {
            pathLengths.remove(key)
        }
    }
    val maxLength = pathLengths.keys.maxOrNull()
    val totalPaths = maxLength?.let { pathLengths[it] } ?: 0

    println("$maxLength $totalPaths")
}
