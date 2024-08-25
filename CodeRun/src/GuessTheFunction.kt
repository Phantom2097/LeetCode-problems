import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    var n = reader.readLine().toInt()
    var ans = n

    var i = 2
    while (i * i <= n) {
        if (n % i == 0) {
            while (n % i == 0) {
                n /= i
            }
            ans -= ans / i
        }
        i++
    }

    if (n > 1) {
        ans -= ans / n
    }

    writer.write(ans.toString())
    reader.close()
    writer.close()
}