package algo6_0.dz3

import java.io.*

fun main() {
    val reader = BufferedReader(FileReader("Algorithms/src/algo6_0/dz3/stress_Stulia.txt"))
    val writer = BufferedWriter(FileWriter("output.txt"))

    val count = reader.readLine().toInt()
    var isRight = true
    var testNumber = -1

    repeat(count) {
        testNumber++
        val (n, h) = reader.readLine().trim().split(' ').map { it.toInt() }

        val heights = reader.readLine().trim().split(' ').map { it.toInt() }

        var i = 0
        val chairs =
            reader.readLine().trim().split(' ').mapTo(mutableListOf<Pair<Int, Int>>()) { it.toInt() to heights[i++] }
        val answer = reader.readLine().trim().toInt()

        chairs.sortBy { it.second }

        val result = bedOfChairs(chairs, n, h)

        if (result != answer) {
            writer.write("$testNumber, $result, $answer\n")
            isRight = false
        }
        reader.readLine()
    }

    writer.write("${isRight}\n")

    writer.close()
    reader.close()
}

fun bedOfChairs(chairs: List<Pair<Int, Int>>, n: Int, h: Int): Int {
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
            if (window.isEmpty() && chairs[i].first >= h) {
                result = 0
            }
            left++
        }
    }
    return result
}