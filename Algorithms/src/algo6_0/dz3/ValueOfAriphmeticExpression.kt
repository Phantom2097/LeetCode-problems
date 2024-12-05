package algo6_0.dz3

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val expression = reader.readLine().trim()

    val symbols = listOf('+', '-', '*', '(', ')', '{', '}', '[', ']')
    val stack = Stack<Char>()
    val postfixNotation = StringBuilder()


    var isWrong = false
    /*
        Всего 100 символов, наверное лучше в начале пройтись по строке, проверить валидная она или нет, попутно строя польскую нотацию,
        а потом уже посчитать
     */
    // Проверяет сразу есть ли в конце строки невалидный символ
    if (expression.last() in listOf('+', '-', '*', '(')) {
        isWrong = true
    }
    var prevCurrentSymbol = ' '
    var spaceBetween = true
    var bracketCount = 0

    /*
        Лучше закинуть в while, так удобнее с пробелами.
     */
    for (i in expression.indices) {
        when (expression[i]) {
            '(' -> {
                bracketCount++
            }
            ')' -> {
                bracketCount--
                if (bracketCount < 0) {
                    isWrong = true
                    break
                }
            }
            else -> {
                if (expression[i].isDigit() && prevCurrentSymbol.isDigit() && spaceBetween) {
                    isWrong = true
                }
            }
        }
    }
    for ((i, ex) in expression.withIndex()) {
        when {
            ex in symbols -> {
                when (ex) {
                    '+' -> {
                        stack.push('+')
                    }
                    '-' -> {
                        stack.push('-')
                    }
                    '*' -> {
                        stack.push('*')
                    }
                    '(' -> {
                        stack.push('(')
                    }
                    ')' -> {
                        while (!stack.empty() && stack.peek() != '(') {
                            postfixNotation.append(stack.pop())
                        }
                        if (stack.peek() == '(') {
                            stack.pop()
                        } else {
                            isWrong = true
                            break
                        }
                    }
                }
                prevCurrentSymbol = ex
            }
            ex.isDigit() -> {
                postfixNotation.append(ex)
                prevCurrentSymbol = ex
            }
            ex == ' ' -> {
                if (prevCurrentSymbol.isDigit() && expression[i + 1].isDigit()) {
                    isWrong = true
                    break
                } else {
                    continue
                }
            }
            else -> {
                isWrong = true
                break
            }
        }
    }

    while (!stack.empty()) {
        postfixNotation.append(stack.pop())
    }

    writer.write(postfixNotation.toString())
    writer.write(isWrong.toString())

    writer.close()
    reader.close()


}
