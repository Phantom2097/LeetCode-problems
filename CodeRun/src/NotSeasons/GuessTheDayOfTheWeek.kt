package NotSeasons

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/*
Это работает, но не проходит по времени
 */

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (N, L) = reader.readLine()!!.split(" ").map { it.toInt() }
    val daysInMonth = reader.readLine()!!.split(" ").map { it.toInt() }

    val (d1, m1, y1, t1) = reader.readLine()!!.split(" ").map { it.toInt() }
    val (d2, m2, y2) = reader.readLine()!!.split(" ").map { it.toInt() }

    fun daysSinceStart(d: Int, m: Int, y: Int): Long {
        var days = 0L

        days += daysInMonth.sum() * y

        for (month in 1 ..< m) {
            days += daysInMonth[month - 1]
        }

        days += (d - 1)
        return days
    }

    val days1 = daysSinceStart(d1, m1, y1)
    val days2 = daysSinceStart(d2, m2, y2)

    val daysDifference = days2 - days1

    val resultDayOfWeek = ((t1 - 1 + daysDifference % L + L) % L + 1).toInt()
    writer.write(resultDayOfWeek.toString())

    writer.close()
    reader.close()
}
