import java.io.BufferedReader
import java.io.InputStreamReader

fun snowHeight(): String {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val n = reader.readLine().toInt()
    val days = reader.readLine().split(" ").map { it.toInt() }

    val snowCountPerDay = IntArray(n)

    var height = 0

    var res = true

    for (i in days.indices) {
        when(days[i]) {
            -1 -> {
                snowCountPerDay[i] = 1
                ++height
            }
            else -> {
                if (days[i] > height) {
                    snowCountPerDay[i] = days[i] - height
                    height = days[i]
                } else {
                    res = false
                    break
                }
            }
        }
    }
    return if (!res) "NO" else "YES\n${snowCountPerDay.joinToString(" ")}"
}
fun main() {
    println(snowHeight())
}