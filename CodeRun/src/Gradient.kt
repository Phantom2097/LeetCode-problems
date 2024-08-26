import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (W, H) = reader.readLine().split(" ").map { it.toInt() }

    val rowSum = LongArray(H)
    val colSum = LongArray(W)

    for (h in 0 ..< H) {
        val rowMultiplier = (h + 1) * (H - h).toLong()
        for (w in 0 ..< W) {
            val colMultiplier = (w + 1) * (W - w).toLong()
            rowSum[h] += colMultiplier
            colSum[w] += rowMultiplier
        }
    }

    for (h in 0 ..< H) {
        rowSum[h] *= (h + 1) * (H - h).toLong()
    }
    for (w in 0 ..< W) {
        colSum[w] *= (w + 1) * (W - w).toLong()
    }
    writer.write(rowSum.joinToString(" ") + "\n" + colSum.joinToString(" "))
    reader.close()
    writer.close()
}