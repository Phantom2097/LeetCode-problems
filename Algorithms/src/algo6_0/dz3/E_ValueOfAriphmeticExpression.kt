package algo6_0.dz3
/*
    Проверить пустые или нет.
    Проверить буквы.
    Проверить знаки идущие подряд.
    Проверить цифры между которыми пробел
    Знак в конце
    знак в начале
 */
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    var expression = reader.readLine().trim()

    var isWrong = false
    val prefix = StringBuilder()
    val result = Stack<Int>()

    if (expression.isBlank() || expression.last().isOperation()) {
        isWrong = true
    } else {
        if (expression[0] == '+' || expression[0] == '-') {
            expression = "0$expression"
        }
        expression = expression.replace("(-", "(0-").replace("(+", "(0+")

        var brackets = 0
        var prevCorrectValue = expression[0]
        if (!expression[0].isDigit() && expression[0] != '(' && expression[0] == ')') isWrong = true
        var haveSpaseBefore = false


        val operationsStack = Stack<Char>()
        if (!prevCorrectValue.isDigit()) {
            operationsStack.push(prevCorrectValue)
            if (prevCorrectValue == '(') brackets++
        } else {
            prefix.append(prevCorrectValue)
        }


        for (i in 1 ..< expression.length) {
            if (isWrong) break
            if (expression[i] == ' ') {
                haveSpaseBefore = true
                continue
            } else {
                if (expression[i].isDigit()) {
                    if (haveSpaseBefore && (prevCorrectValue.isDigit() || prevCorrectValue == ')')) {
                        isWrong = true
                    } else {
                        prefix.append(expression[i])
                        prevCorrectValue = expression[i]
                        haveSpaseBefore = false
                    }
                } else if (expression[i] == '(') {
                    if (prevCorrectValue.isOperation() || expression[i] == '(') {
                        operationsStack.push(expression[i])
                        prevCorrectValue = expression[i]
                        haveSpaseBefore = false
                        brackets++
                    } else {
                        isWrong = true
                    }
                } else if (expression[i].isOperation()) {
                    if (prevCorrectValue.isDigit() || prevCorrectValue == ')') {
                        if (expression[i] == '+' || expression[i] == '-') {
                            while (operationsStack.isNotEmpty() && operationsStack.peek() != '(') {
                                prefix.append(operationsStack.pop())
                            }
                        }
                        if (prevCorrectValue.isDigit()) prefix.append(" ")
                        operationsStack.push(expression[i])
                        prevCorrectValue = expression[i]
                        haveSpaseBefore = false
                    } else {
                        isWrong = true
                    }
                } else if (expression[i] == ')') {
                    if (prevCorrectValue.isOperation() || prevCorrectValue == '(') {
                        isWrong = true
                    } else if (brackets > 0) {
                        while (operationsStack.peek() != '(') {
                            prefix.append(operationsStack.pop())
                        }
                        operationsStack.pop()
                        brackets--
                    } else {
                        isWrong = true
                    }
                } else {
                    isWrong = true
                }
            }
        }
        if (brackets > 0) {
            isWrong = true
        }
        if (!isWrong) {
            while (operationsStack.isNotEmpty()) {
                prefix.append(operationsStack.pop())
            }
        }
        val temp = StringBuilder()
        for (p in prefix.toString()) {
            if (isWrong) break
            if (p.isDigit()) {
                temp.append(p)
            } else {
                if (temp.isNotEmpty()) {
                    result.push(temp.toString().toInt())
                    temp.clear()
                }
                when (p) {
                    '+' -> {
                        val last = result.pop()
                        val prev = result.pop()
                        result.push(prev + last)
                    }

                    '-' -> {
                        val last = result.pop()
                        val prev = result.pop()
                        result.push(prev - last)
                    }

                    '*' -> {
                        if (result.size > 1 ) {
                            val last = result.pop()
                            val prev = result.pop()
                            result.push(prev * last)
                        } else {
                            isWrong = true
                        }
                    }

                    else -> continue
                }

            }
        }
        if (temp.isNotEmpty()) {
            result.push(temp.toString().toInt())
        }
    }



    writer.write(if (isWrong) "WRONG" else "${result.pop()}\n")

    writer.close()
    reader.close()
}

private fun Char.isBracket(): Boolean = this == '(' || this == ')'

private fun Char.isOperation(): Boolean = this == '-' || this == '+' || this == '*'