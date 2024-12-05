package algo6_0.dz3

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (n, b) = reader.readLine()!!.trim().split(' ').map { it.toInt() }
    val clients = reader.readLine()!!.trim().split(' ').mapTo(ArrayList<Int>()) { it.toInt() }

    var result = 0L
    var time = 0

    val deque = ArrayDeque<Pair<Int, Int>>()

    /*
        Сложность с проверкой, когда меньше, чем максимальное количествоь
     */
    for (i in 0 ..< n) {
        deque.add(clients[i] to i)
        var count = b
        do {
            val firstPerson = deque.removeFirst()
            result += (time - firstPerson.second + 1).toLong() * minOf(firstPerson.first, count)
            if (firstPerson.first - count > 0) {
                deque.addFirst(firstPerson.first - count to firstPerson.second)
            }
            count -= minOf(firstPerson.first, count)
        } while (deque.isNotEmpty() && time > firstPerson.second && count != 0)
        time++
    }

    while (deque.isNotEmpty()) {
        val lostPerson = deque.removeFirst()
        result += (maxOf(time - lostPerson.second + 1, 1).toLong() * lostPerson.first)
    }

    writer.write(result.toString() + "\n")

    writer.close()
    reader.close()
}