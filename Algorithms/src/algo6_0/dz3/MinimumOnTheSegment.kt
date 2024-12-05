package algo6_0.dz3

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.collections.ArrayDeque

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (n, k) = reader.readLine().split(' ').map { it.toInt() }

    val numbers = reader.readLine().trim().split(' ').map { it.toInt() }

    val window = ArrayDeque<Int>()
    val res = mutableListOf<Int>()

    for (i in numbers.indices) {
        if (window.isNotEmpty() && window.first() <= i - k) {
            window.removeFirst()
        }

        while (window.isNotEmpty() && numbers[window.last()] >= numbers[i]) {
            window.removeLast()
        }

        window.add(i)

        if (i >= k - 1) {
            res.add(numbers[window.first()])
        }
    }
    writer.write(res.joinToString("\n"))

    writer.close()
    reader.close()
}