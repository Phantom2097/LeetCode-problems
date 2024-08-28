import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


/*
	массив с буквами, в которых хранится суммарное время
    счтить вычитая из текущего прошлый
    вернуть символ с самым большим временем
*/
fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val countLetters = IntArray(26)

    val n = reader.readLine().toInt()
    val row = reader.readLine()
    val time = reader.readLine().split(" ").map { it.toInt() }

    var maxTime = time[0]
    var res = row[0]

    for (i in 1 ..< n) {
        val temp = time[i] - time[i - 1]
        if (temp >= maxTime) {
            maxTime = temp
            res = row[i]
        }
    }

    writer.write(res.toString())

    reader.close()
    writer.close()
}