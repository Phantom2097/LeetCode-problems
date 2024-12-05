import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

 /*
    Иногда нужно хранить первое и последнее значение в дне, чтобы правильно определять, когда наступит следующий
    Ещё можно удалить дату в начале дня, если она тоже не играет роли
3
00:00:00
23:00:00
23:00:00
тут можно удалить первую
  */

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()
    val dates = IntArray(n)
    for (i in 0 ..< n) {
        val time = reader.readLine().split(":").map { it.toInt() }
        dates[i] = time[0] * 3600 + time[1] * 60 + time[2]
    }
    var minDaysCount = 1
    for (i in 1 ..< n) {
        if (dates[i - 1] >= dates[i]) minDaysCount++
    }

    var currentDayStart = dates[0]
    var currentDayEnd = dates[1]
    var records = 0

    for (i in 1 ..< n) {
        if (dates[i] <= currentDayEnd) {
            currentDayStart = dates[i]
            currentDayEnd = dates[i]
        } else {
            currentDayEnd = dates[i]
            if (currentDayEnd > currentDayStart) records++
        }
    }
    if (dates[0] < dates[1]) {
        records++
    }
    if (dates[n - 2] < dates[n - 1]) {
        records++
    }

    writer.write((records).toString())

    reader.close()
    writer.close()
}