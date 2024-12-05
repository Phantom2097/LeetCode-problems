package algo6_0.dz3

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (n, h) = reader.readLine().trim().split(' ').map { it.toInt() }

    val heights = reader.readLine().trim().split(' ').map { it.toInt() }

    var i = 0
    val chairs = reader.readLine().trim().split(' ').mapTo(mutableListOf<Pair<Int, Int>>()) { it.toInt() to heights[i++] }

    chairs.sortBy { it.second }


    val window = ArrayDeque<Pair<Int, Int>>() // index, difference
    var left = 0
    var width = 0
    var prev = 0

    var result = Int.MAX_VALUE

    for (i in chairs.indices) {
        val differenceInHeight = chairs[i].second - chairs[prev].second
        prev = i
        width += chairs[i].first

        while (window.isNotEmpty() && window.last().second <= differenceInHeight) {
            window.removeLast()
        }
        window.addLast(i - 1 to differenceInHeight)
        if (width >= h) result = minOf(result, window.first().second)

        while (left < i && width - chairs[left].first >= h) {
            width -= chairs[left].first
            if (window.isNotEmpty() && window.first().first == left) {
                window.removeFirst()
            }
            if (window.isNotEmpty()) {
                result = minOf(result, window.first().second)
            }
            if (chairs[i].first >= h) {
                result = 0
            }
            left++
        }
        if (result == 0) break
    }

    writer.write("${result}\n")

    writer.close()
    reader.close()
}