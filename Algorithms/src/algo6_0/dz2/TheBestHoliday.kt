package algo6_0.dz2

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }

    val doings = readln().split(" ").mapTo(mutableListOf<Int>()) { it.toInt() }

    doings.sort()

    var maxDays = 1

    var right = 0
    var left = 0

    while (right < n) {
        while (right < n && doings[right] - k <= doings[left]) {
            right++
            maxDays = maxOf(right - left, maxDays)
        }
        left++
    }
    println(maxDays)
}