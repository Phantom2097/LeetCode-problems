package algo6_0.dz2

fun main() {
    val n = readln().toInt()
    var acc = 0
    val arr = readln().split(" ").map { acc += it.toInt(); acc }

    println(arr.joinToString(" "))
}