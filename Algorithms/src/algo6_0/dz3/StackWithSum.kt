package algo6_0.dz3

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().trim().toInt()

    val stack = MyStack()

    repeat(n) {
        val line = reader.readLine().trim()
        val operation = line[0]
        val num = if (operation != '-') line.substring(1).trim().toInt() else 0

        when (operation) {
            '+' -> stack.addNumber(num)
            '-' -> writer.write("${stack.removeLastElement()}\n")
            '?' -> writer.write("${stack.sumLastCount(num)}\n")
        }
    }
    reader.close()
    writer.close()
}

class MyStack() {
    private val stack = arrayListOf<Int>()
    private val prefix = arrayListOf<Long>(0L)
    private var size = 0

    fun removeLastElement(): Int {
        val temp = stack.removeLast()
        prefix.removeAt(size)
        size--
        return temp
    }

    fun addNumber(num: Int) {
        stack.add(num)
        prefix.add(num.toLong() + prefix[size])
        size++
    }

    fun sumLastCount(count: Int): Long {
        return prefix[size] - prefix[size - count]
    }
}

/*
12
+1000000000
+1000000000
+1000000000
+1000000000
+1000000000
+1000000000
+1000000000
+1000000000
+1000000000
+1000000000
?10
?4
 */

/*
10000000000
4000000000
 */