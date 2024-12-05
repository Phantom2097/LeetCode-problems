package algo6_0.dz3

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().trim().toInt()

    val weight = reader.readLine().trim()

    val startSubSequence = reader.readLine().trim()

    writer.write(resultSequence(n, weight, startSubSequence))

    reader.close()
    writer.close()
}

fun resultSequence(n: Int, weight: String, startSubSequence: String): String {
    if (n == startSubSequence.length) return startSubSequence
    
    val result = StringBuilder(startSubSequence)
    val endStack = Stack<Char>()

    val bracketsWeight = mutableMapOf<Char, Int>()

    weight.forEachIndexed { i, c ->
        bracketsWeight[c] = i
    }

    startSubSequence.forEach {
        when (it) {
            '(' -> endStack.push(')')
            '[' -> endStack.push(']')
            ')' -> endStack.pop()
            ']' -> endStack.pop()
        }
    }

    while (result.length + endStack.size < n) {
        val lastChar = result.last()
        var addedValue: Char
        if (isOpen(lastChar)) {
            addedValue =
                if (bracketsWeight[suitable(lastChar)!!]!! < bracketsWeight['(']!! && bracketsWeight[suitable(lastChar)!!]!! < bracketsWeight['[']!!) {
                    suitable(lastChar)!!
                } else {
                    if (bracketsWeight['(']!! < bracketsWeight['[']!!) {
                        '('
                    } else {
                        '['
                    }
                }

        } else {
            addedValue = if (endStack.isNotEmpty() && bracketsWeight[endStack.peek()]!! < bracketsWeight['(']!! && bracketsWeight[endStack.peek()]!! < bracketsWeight['[']!!) {
                endStack.peek()
            } else {
                if (bracketsWeight['(']!! < bracketsWeight['[']!!) {
                    '('
                } else {
                    '['
                }
            }
        }

        if (isOpen(addedValue)) {
            val suit = suitable(addedValue)!!
            result.append(addedValue)
            endStack.push(suit)
        } else {
            result.append(addedValue)
        }

        if (endStack.isNotEmpty() && endStack.peek() == result.last()) {
            endStack.pop()
        }
    }

    while (endStack.isNotEmpty()) {
        result.append(endStack.pop())
    }

    return result.toString()
}

private fun isOpen(c: Char): Boolean = (c == '(') || (c == '[')

private fun suitable(c: Char): Char? = when (c) {
    '(' -> ')'
    '[' -> ']'
    else -> null
}