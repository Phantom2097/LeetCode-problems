package algo6_0.dz2

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }

    val carNumbers = readln().split(" ").map { it.toInt() }

    var left = 0
    var sum = 0
    var count = 0

    for (right in 0 ..< n) {
        sum += carNumbers[right]

        while (sum > k) {
            sum -= carNumbers[left++]
        }

        if (sum == k) {
            count++
        }
    }
    println(count)
}