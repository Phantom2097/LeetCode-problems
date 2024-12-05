import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))


    val (k, t) = reader.readLine().split(" ").map { it.toLong() }

    var totalCoverage: Long = 0

    for (i in 0..< k) {
        val (start, end, brightness) = reader.readLine().split(" ").map { it.toLong() }
        totalCoverage += minOf(brightness, end - start + 1)
    }

    if (totalCoverage >= t) {
        writer.write("Yes")
    } else {
        writer.write("No")
    }

    reader.close()
    writer.close()
}
