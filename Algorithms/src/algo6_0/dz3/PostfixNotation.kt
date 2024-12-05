package algo6_0.dz3

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val expression = reader.readLine().trim().split(" ")

    val stack = Stack<Int>()

    expression.forEach {
        if (it[0].isDigit()) {
            stack.push(it[0] - '0')
        } else {
            when (it) {
                "+" -> {
                    val last = stack.pop()
                    val prev = stack.pop()
                    stack.push(prev + last)
                }
                "-" -> {
                    val last = stack.pop()
                    val prev = stack.pop()
                    stack.push(prev - last)
                }
                "*" -> {
                    val last = stack.pop()
                    val prev = stack.pop()
                    stack.push(prev * last)
                }
            }
        }
    }

    writer.write(stack.pop().toString())

    reader.close()
    writer.close()
}