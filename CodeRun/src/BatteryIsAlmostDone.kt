import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()
    val apps = reader.readLine().split(" ").sumOf { it.toInt() }

    val res = 100 / apps
    writer.write(res.toString())

    reader.close()
    writer.close()
}