import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun levenshteinDistance(a: String, b: String): Int {
    val dp = Array(a.length + 1) { IntArray(b.length + 1) }
    for (i in 0..a.length) dp[i][0] = i
    for (j in 0..b.length) dp[0][j] = j
    for (i in 1..a.length) {
        for (j in 1..b.length) {
            val cost = if (a[i - 1] == b[j - 1]) 0 else 1
            dp[i][j] = min(min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost)
        }
    }
    return dp[a.length][b.length]
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()
    val word = reader.readLine()
    val m = reader.readLine().toInt()

    var bestWord: String? = null
    var minDistance = Int.MAX_VALUE

    repeat(m) {
        val len = reader.readLine().toInt()
        val dictWord = reader.readLine()

        val distance = levenshteinDistance(word, dictWord)

        if (distance <= 10) {
            if (distance < minDistance) {
                minDistance = distance
                bestWord = dictWord
            } else if (distance == minDistance) {
                if (bestWord == null || dictWord < bestWord!!) {
                    bestWord = dictWord
                }
            }
        }
    }

    writer.write(bestWord ?: "NO MATCH")

    reader.close()
    writer.close()
}
