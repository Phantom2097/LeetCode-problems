package algo6_0.dz3

import java.util.*

fun main() {
    val line = readln()

    val stack = Stack<Char>()
    var isFalse = false

    for (bracket in line) {
        when (bracket) {
            '(' -> stack.push(bracket)
            ')' -> if (stack.isNotEmpty() && stack.peek() == '(') stack.pop() else {
                isFalse = true
                break
            }
            '[' -> stack.push(bracket)
            ']' -> if (stack.isNotEmpty() && stack.peek() == '[') stack.pop() else {
                isFalse = true
                break
            }
            '{' -> stack.push(bracket)
            '}' -> if (stack.isNotEmpty() && stack.peek() == '{') stack.pop() else  {
                isFalse = true
                break
            }
        }
    }
    if (stack.isNotEmpty() || isFalse) {
        println("no")
    } else {
        println("yes")
    }
}