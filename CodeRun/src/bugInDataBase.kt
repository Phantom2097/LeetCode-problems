import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val lines = HashMap<Int, String>()

    while (true) {
        val line = reader.readLine()
        if (line.isNullOrBlank()) break
        val (digit, notDigit) = line.toCharArray().partition { it.isDigit() }
        lines[digit.joinToString("").toInt()] = notDigit.joinToString("")
    }

    lines.toSortedMap()

    writer.write(lines.values.joinToString("\n"))

    reader.close()
    writer.close()
}