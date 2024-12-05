import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val len = reader.readLine().toInt()
    val line = reader.readLine()

    if (len % 2 == 1) {
        writer.write("0")
    } else {
        val stack = Stack<Char>()
        for (c in line) {
            if (stack.isNotEmpty()) {
                val prev = stack.peek()
                if (prev == c) {
                    stack.pop()
                } else {
                    stack.push(c)
                }
            } else {
                stack.push(c)
            }
        }
        if (stack.isNotEmpty()) {
            writer.write("0")
        } else {
            writer.write("1")
        }
    }

    reader.close()
    writer.close()
}