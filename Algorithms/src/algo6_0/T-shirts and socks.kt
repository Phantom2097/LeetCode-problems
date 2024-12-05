package algo6_0

// 10 7 0 4 -> 11 1
// 3 3 4 7 -> 4 1
// 9 0 5 2 -> 1 3
// 114 299 921 166 -> 300 1
// 790 493 507 302 -> 1 508
// 1 1 1 1 -> 2 1
// 2 10 3 9 -> 3 4
// 8 9 5 9 -> 10 1
// 91147 42758 49259 89684 ->

fun main() {

    val a = readln().toInt()
    val b = readln().toInt()

    val c = readln().toInt()
    val d = readln().toInt()

    val (x, y) = attemptCounter(a, b, c, d)

    println("$x $y")
}

fun attemptCounter(a: Int, b: Int, c: Int, d: Int): Pair<Int, Int> = when {
    a == 0 -> Pair(1, c + 1)
    b == 0 -> Pair(1, d + 1)
    c == 0 -> Pair(a + 1, 1)
    d == 0 -> Pair(b + 1, 1)
    a == b && c == d -> if (a <= c) Pair(a + 1, 1) else Pair(1, c + 1)
    a == b -> if (a < maxOf(c, d)) Pair(a + 1, 1) else Pair(1, maxOf(c, d) + 1)
    c == d -> if (c < maxOf(a, b)) Pair(a, c + 1) else Pair(maxOf(a, b) + 1, 1)
    (a + b > c + d) -> {
        if (a < b) {
            if (c < d) {
                if (a + c < minOf(b, d)) Pair(a + 1, c + 1)
                else if (b < d) Pair(b + 1, 1)
                else Pair(1, d + 1)
            } else {
                if (b + d < c) Pair(b + 1, d + 1)
                else Pair(1, c + 1) // 3 7 6 2 -> 1 7
            }
        } else {
            if (c < d) {
                Pair(1, d + 1)
            } else {
                if (b + d < minOf(a, c)) Pair(b + 1, d + 1)
                else if (a < c) {
                    Pair(a + 1, 1)
                } else {
                    Pair(1, c + 1)
                }
            }
        }
    }
    (a + b < c + d) -> {
        if (c < d) {
            if (a < b) {
                if (a + c < minOf(d, b)) Pair(a + 1, c + 1)
                else if (b < d) Pair(b + 1, 1)
                else Pair(1, d + 1)
            } else {
                if (b + c < minOf(a, d)) Pair(b + 1, c + 1)
                else if (a < d) Pair(a + 1, 1) // 6 2 3 7 -> 7 1
                else Pair(1, d + 1)
            }
        } else {
            if (a < b) {
                Pair(b + 1, 1) // 114 299 921 166 -> 300 1
            } else {
                if (b + d < minOf(a, c)) Pair(b + 1, d + 1) // 6 2 7 3
                else if (a < c) Pair(a + 1, 1)
                else Pair(1, c + 1)
            }
        }
    }
    else -> Pair(minOf(a, b) + 1, minOf(c, d) + 1) // 1 1 1 1 -> 2 1
}