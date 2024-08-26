import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val row = reader.readLine().split(" ").map { it.toInt() }
    val allNumbers = row.size

    val list = ArrayList<Int>()

    var i = 0
    while (i < row.size) {
        if (list.size >= 2) {
            if (list[list.size - 2] == row[i] && list[list.size - 1] == row[i]) {
                val removable = row[i]
                while (i < row.size && row[i] == removable) {
                    i++
                }
                while (list.isNotEmpty() && list.last() == removable) {
                    list.removeLast()
                }
            } else {
                list.add(row[i])
                i++
            }
        } else {
            list.add(row[i])
            i++
        }
    }
//    if (list.size > 2) {
//        if (list[list.size - 3] == list[list.size - 1] && list[list.size - 2] == list[list.size - 1]) {
//            list.removeLast()
//            list.removeLast()
//            list.removeLast()
//        }
//    }

    writer.write((allNumbers - list.size).toString())

    reader.close()
    writer.close()
}