import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter



fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    val list = reader.readLine().split(" ")
    val root = reader.readLine()

    // Попробовать сделать map<String, Boolean>, если можно из прошлой, то можно и в эту, хотя наверное не прокатит.
    val set = mutableSetOf(root)
    val map = mutableListOf<List<String>>()

    repeat(m) {
        val (first, second) = reader.readLine().split(" ")
        map.add(listOf(first, second))
    }

//    var prevSize = 0
    var idx = 0
    while (set.size != n && idx < set.size) {
        val nextNode = set.elementAt(idx)
        for (l in map) {
            if (!set.contains(l.last()) && nextNode == l.first()) {
                set.add(l.last())
            }
        }
        idx++
    }


    writer.write((n - set.size).toString())

    reader.close()
    writer.close()
}