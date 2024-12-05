package algo6_0.dz3

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().trim().toInt()
    val line = reader.readLine().trim()
    val a = line[0] -'0'
    val b = line.substring(1).trim().toInt()

    val comparator = compareBy<IntArray> { it[0] }    // time

    val arr = arrayOf(
        PriorityQueue<IntArray>(comparator),
        PriorityQueue<IntArray>(comparator),
        PriorityQueue<IntArray>(comparator),
        PriorityQueue<IntArray>(comparator),
    )

    var first: Int = 0
    var second: Int = 0
    var third: Int = 0
    var fourth: Int = 0
    when ((a + b) % 2) {
        1 -> {
            if (isRight(a, b)) {
                first = b % 4
                second = a % 4
                third = (b + 2) % 4
                fourth = (a + 2) % 4
            } else {
                first = a % 4
                second = b % 4
                third = (a + 2) % 4
                fourth = (b + 2) % 4
            }
        }
        0 -> {
            first = a % 4
            second = b % 4
            third = (a + 1) % 4
            fourth = (b + 1) % 4
        }
        else -> {}
    }

    for (i in 0 ..< n) {
        val (road, time) = reader.readLine().trim().split(" ").map { it.toInt() }
        arr[road % 4].add(intArrayOf(time, i, road % 4)) // time, index, road number, road
    }

    var roversCount = n

    var nowTime = 0
    val result = IntArray(n)

    while (roversCount > 0) {
        if ((a + b) % 2 == 0) {
            var isNotPassed = true
            if (arr[first].isNotEmpty() && arr[first].peek()[0] <= nowTime) {
                val temp = arr[first].poll()
                result[temp[1]] = nowTime
                isNotPassed = false
                roversCount--
            }
            if (arr[second].isNotEmpty() && arr[second].peek()[0] <= nowTime) {
                val temp = arr[second].poll()
                result[temp[1]] = nowTime
                isNotPassed = false
                roversCount--
            }
            if (isNotPassed) {
                if (arr[third].isNotEmpty() && arr[third].peek()[0] <= nowTime) {
                    val temp = arr[third].poll()
                    result[temp[1]] = nowTime
                    roversCount--
                }
                if (arr[fourth].isNotEmpty() && arr[fourth].peek()[0] <= nowTime) {
                    val temp = arr[fourth].poll()
                    result[temp[1]] = nowTime
                    roversCount--
                }
            }
        } else {
            if (arr[first].isNotEmpty() && arr[first].peek()[0] <= nowTime) {
                val temp = arr[first].poll()
                result[temp[1]] = nowTime
                roversCount--
            } else if (arr[second].isNotEmpty() && arr[second].peek()[0] <= nowTime) {
                val temp = arr[second].poll()
                result[temp[1]] = nowTime
                roversCount--
            } else if (arr[third].isNotEmpty() && arr[third].peek()[0] <= nowTime) {
                val temp = arr[third].poll()
                result[temp[1]] = nowTime
                roversCount--
            } else if (arr[fourth].isNotEmpty() && arr[fourth].peek()[0] <= nowTime) {
                val temp = arr[fourth].poll()
                result[temp[1]] = nowTime
                roversCount--
            }
        }
        nowTime++
    }

    writer.write(result.joinToString(separator = "\n", postfix = "\n"))
    reader.close()
    writer.close()
}

private fun isRight(roadA: Int, roadB: Int): Boolean {
    return roadA - roadB % 4 == 1
}