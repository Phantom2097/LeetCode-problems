import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))


    val n = reader.readLine().toInt()
    val words = mutableMapOf<String, Int>()

    for (i in 1 .. n) {
        val request = reader.readLine().split(" ")
        if (request[0] == "+") {
            val count = request[1].toInt()
            val word = request[2]
            words[word] = words.getOrDefault(word, 0) + count
        } else {
            val prefix = request[1]
            val candidates = words.filterKeys { it.startsWith(prefix) }

            if (candidates.isEmpty()) {
                writer.write(prefix + "\n")
            } else {
                val bestWord = candidates.entries.maxWithOrNull(compareBy({ it.value }, { -it.key.length }, { it.key }))?.key
                writer.write(bestWord + "\n")
            }
        }
    }
    reader.close()
    writer.close()
}