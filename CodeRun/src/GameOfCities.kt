import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val first = reader.readLine()
    val second = reader.readLine()

    val len1 = first.length
    val len2 = second.length

    if (len1 == len2) {
        var firstCount = 0L
        var secondCount = 0L
        for (i in 0 ..< len1) {
            firstCount += first[i].code
            secondCount += second[i].code
        }
        if (firstCount != secondCount) {
            writer.write("NO")
        } else {
            writer.write("YES")
        }
    } else {
        writer.write("NO")
    }
    reader.close()
    writer.close()
}