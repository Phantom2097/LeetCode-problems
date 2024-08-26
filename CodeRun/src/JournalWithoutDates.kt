import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter



fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()
    val dates =  Array(n) { IntArray(3) }
    for (i in 0 ..< n) {
        val time = reader.readLine().split(":").map { it.toInt() }
        dates[i] = time.toIntArray()
    }
    var (prevHours, prevMinutes, prevSeconds) = dates[0]
    var days = 1

    for (i in 1 ..< dates.size) {
        when {
            dates[i][0] < prevHours -> days++
            dates[i][0] == prevHours -> {
                when {
                    dates[i][1] < prevMinutes -> days++
                    dates[i][1] == prevMinutes -> if (dates[i][2] <= prevSeconds) days++
                }
            }
        }
        prevHours = dates[i][0]
        prevMinutes = dates[i][1]
        prevSeconds = dates[i][2]
    }

    writer.write(days.toString())

    reader.close()
    writer.close()
}