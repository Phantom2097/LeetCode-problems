package algo6_0

fun disabledSquareFind(
    startY: Int,
    startX: Int,
    endY: Int,
    endX: Int,
    list: Array<BooleanArray>
) {
    data class Square(val left: Int, val up: Int, var right: Int = 0, var bottom: Int = 0)

    val disabledLights = mutableSetOf<Pair<Int, Int>>()
    val disabledSquares = mutableSetOf<Square>()
    for (i in startY..endY) {
        for (j in startX..endX) {
            if (!list[i][j] && !disabledLights.contains(Pair(i, j))) {
                disabledLights.add(Pair(i, j))
                val newSquare = Square(j, i)
                var itPossible = true
                var dx = j
                while (itPossible) {
                    when {
                        dx + 1 <= endX && !list[i][dx + 1] && !disabledLights.contains(Pair(i, dx + 1)) -> {
                            disabledLights.add(Pair(i, ++dx))
                        }
                        else -> {
                            newSquare.right = dx
                            newSquare.bottom = i
                            itPossible = false
                        }
                    }
                }
                if (disabledSquares.isNotEmpty()) {
                    val prev = disabledSquares.last()
                    if (
                        prev.left == newSquare.left &&
                        prev.right == newSquare.right &&
                        prev.bottom + 1 == newSquare.up
                    ) {
                        prev.bottom = newSquare.bottom
                    } else {
                        disabledSquares.add(newSquare)
                    }
                } else {
                    disabledSquares.add(newSquare)
                }
            }
        }
    }

    when {
        disabledLights.isEmpty() -> println("I")
        disabledSquares.size == 1 -> {
            val square = disabledSquares.first()
            // Проверяет левый и нижний края
            if (startX != square.left && endY != square.bottom) {
                // проверяет правый край
                if (endX != square.right) {
                    // проверяет верхний край
                    if (startY != square.up) {
                        println("O")
                    } else {
                        println("X")
                    }
                } else {
                    if (startY != square.up) {
                        println("C")
                    } else {
                        println("L")
                    }
                }
            } else {
                println("X")
            }
            /*
            когда начало равно startY и конец равен endX и остальные не равны, то это L
            если равна только endX, то это C
            если не равно всем, то это О
            иначе это Х
             */
        }
        disabledSquares.size == 2 -> {
            val s1 = disabledSquares.first()
            val s2 = disabledSquares.last()
            if (s1.left == s2.left) {
                if (s1.bottom + 1 >= s2.up) {
                    println("X")
                } else {
                    if (s1.right == s2.right) {
                        if (s1.up == startY && s2.bottom == endY) {
                            println("H")
                        } else {
                            println("X")
                        }
                    } else {
                        if (
                            s2.right == endX &&
                            s2.bottom == endY &&
                            s1.up != startY
                        ) {
                            println("P")
                        } else {
                            println("X")
                        }
                    }
                }
            } else {
                println("X")
            }

            // значит равно двум
            // если startY и endY совпадают с разными
            // 1 - если левые и правые стороны равны -> H
            // 2 - если левые равны и нижняя правая равна endX и endY.
            // иначе X
            // ещё они обязательно друг над другом, если один слева, другой справа, то это не считается
            // ещё надо учесть, чтобы между ними был пробел.
        }
        else -> println("X")
    }
}

fun main() {
    val l = readln().toInt()
    // I, O, C, L, H, P иначе X
    val list = Array(l + 1) { BooleanArray(l + 1) { true } }

    var startY = Int.MAX_VALUE; var startX = Int.MAX_VALUE
    var endY = Int.MIN_VALUE; var endX = Int.MIN_VALUE

    for (i in 0..<l) {
        val line = readln()
        for (j in 0..<l) {
            if (line[j] != '#') {
                list[i][j] = false
            } else {
                startY = minOf(startY, i)   // верх
                startX = minOf(startX, j)   // лево
                endY = maxOf(endY, i)       // низ
                endX = maxOf(endX, j)       // право
            }
        }
    }
    when {
        startY == Int.MAX_VALUE || !list[startY][startX] -> println("X")
        startY == endY && startX == endX -> println("I")
        else -> {
            disabledSquareFind(startY, startX, endY, endX, list)
        }
    }
}

