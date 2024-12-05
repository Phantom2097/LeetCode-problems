package algo6_0.dz2

fun main() {
    val (n, r) = readln().split(" ").map { it.toInt() }

    val monuments = readln().split(" ").map { it.toInt() }
    var count = 0L
    var number = 1
    for (i in 0 ..< n - 1) {
        while (number < n && monuments[number] - monuments[i] <= r) number++
        count += n - number
    }
    println(count)
}