package algo6_0.dz2

fun main() {
    val n = readln().toInt()
    val arr = readln()
        .split(" ")
        .map { it.toInt() }

    var sum = 0L

//    var prev = 0L
    val suffix = LongArray(n)
    val suffixProducts = LongArray(n)
//    val result = LongArray(n)
    for (i in n - 2 downTo 0) {
        suffix[i] = suffix[i + 1] + arr[i + 1]
        suffixProducts[i] = (arr[i] * suffix[i] + suffixProducts[i + 1]) % 1_000_000_007L
    }
    for (i in 0 ..< n - 1) {
        sum = (sum + (arr[i] * suffixProducts[i + 1]) % 1_000_000_007L) % 1_000_000_007L
    }
    suffix.max()
    println(sum)
//    println(suffix.joinToString(" "))
//    println(suffixProducts.joinToString(" "))
//
//    println(arr.joinToString(" "))
}