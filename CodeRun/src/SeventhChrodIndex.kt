import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }.sorted()

    if (arr[n - 1] < 1) {
        writer.write("0")
    } else {
        var current = 0
        do {
            current++
        } while ((n - current + 1 > 0) && (current * current <= arr[n - current]))
        writer.write((--current).toString())
    }

    reader.close()
    writer.close()
}