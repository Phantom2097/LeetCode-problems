import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val firstLine = reader.readLine().split(" ")
    val n = firstLine[0].toInt() // Количество статей
    val k = firstLine[1].toInt() // Количество упоминаний слова goose для применения фильтра

    val list = mutableSetOf<String>()

    for (i in 0 ..< n) {
        val site = reader.readLine().toString()
        val lineCount = reader.readLine().toString().split(" ").count { it == "goose" }

        if (lineCount >= k) {
            list.add(site)
        }
    }
    writer.write(list.size.toString() + "\n")
    writer.write(list.sorted().joinToString("\n"))

    reader.close()
    writer.close()
}