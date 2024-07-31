import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


public class OpenCalculator {
    fun openCalculator(inputButtons: List<Int>, number: Int): String {
        val numbers = IntArray(10) {0}
        var num = number
        var res = 0
        while (num > 0) {
            numbers[num % 10]++
            num /= 10
        }
        for (i in numbers.indices) {
            if ((numbers[i] != 0) && (i !in inputButtons)) {
                res++
            }
        }
        return res.toString() // BufferedWriter принимает String
    }
}
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val inputButtons = reader.readLine().split(" ").map { it.toInt() }
    val number = reader.readLine().toInt()

    writer.write(OpenCalculator().openCalculator(inputButtons, number))


    reader.close()
    writer.close()
}