import java.io.*
import java.util.*

fun dateToSeconds(day: Int, month: Int, year: Int): Int {
    val calendar = Calendar.getInstance()
    calendar.set(year, month - 1, day, 0, 0, 0)
    return (calendar.timeInMillis / 1000).toInt()
}

fun secondsToDate(seconds: Int): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = (seconds.toLong() * 1000)
    val day = calendar.get(Calendar.DAY_OF_MONTH).toString().padStart(2, '0')
    val month = (calendar.get(Calendar.MONTH) + 1).toString().padStart(2, '0')
    val year = calendar.get(Calendar.YEAR)
    return "$day.$month.$year"
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val maxAttempts = reader.readLine().toInt()

    val startDateSeconds = dateToSeconds(1, 1, 1970)
    val endDateSeconds = dateToSeconds(31, 12, 2020)

    var low = startDateSeconds
    var high = endDateSeconds

    var attempts = 0

    while (low <= high && attempts < maxAttempts) {
        val mid = (low + high) / 2
        val queryDate = secondsToDate(mid)

        writer.write("? $queryDate\n")
        writer.flush()

        val response = reader.readLine().trim()

        if (response.startsWith("!")) {
            break
        }

        val numDates = response.substring(2).toInt()

        // Determine if we need to move left or right
        if (numDates < (mid - startDateSeconds)) {
            low = mid + 1
        } else {
            high = mid - 1
        }

        attempts++
    }

    // If attempts exhausted without finding the date
    if (attempts >= maxAttempts) {
        writer.write("!\n")
        writer.flush()
    }

    reader.close()
    writer.close()
}
