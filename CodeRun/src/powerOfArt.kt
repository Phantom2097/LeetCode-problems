import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (n, _) = reader.readLine().split(" ").map { it.toInt() }

    // Везде короче нужен Long, либо упустил момент, где надо было самому преобразовать
    val criteria = reader.readLine().split(" ").map { it.toLong() }.filter{ it != 0L}

    val m = criteria.size
    var answer= 0L
    val prefix = LongArray(criteria.size + n + 1)

    for (i in m - 1 downTo 0) {
        prefix[i] += criteria[i] + prefix[i + 1]
    }
    for (i in 0..< m) {
        val ai = criteria[i]
        answer += ai * (ai - 1) + prefix[i] - prefix[i + ai.toInt() + 1]
    }

    writer.write(answer.toString())

    reader.close()
    writer.close()
}