package algo6_0.dz3

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()
    val citys = reader.readLine().split(" ").map { it.toInt() }

    val result = IntArray(n)

    val stack = Stack<Pair<Int, Int>>()

    for (i in n - 1 downTo 0) {
         while (stack.isNotEmpty() && stack.peek().second >= citys[i]) {
             stack.pop()
        }
        if (stack.isEmpty()) {
            stack.push(i to citys[i])
            result[i] = -1
        } else {
            result[i] = stack.peek().first
            stack.push(i to citys[i])
        }
    }
    writer.write(result.joinToString(" "))

    reader.close()
    writer.close()
}