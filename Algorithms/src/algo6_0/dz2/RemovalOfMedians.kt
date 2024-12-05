package algo6_0.dz2

/*
    тест
    2
    1 2
    должно быть 1 2
 */
fun main() {
    val n = readln().toInt()
    val arr = readln().split(" ").mapTo(mutableListOf<Int>()) { it.toInt() }.sorted()

    val half = n / 2
    val res = IntArray(n)
    var start = 0
    var left = half - 1
    var right = half

    if (n % 2 != 0) {
        res[start++] = arr[right++]
    }

    repeat(half) {
        res[start++] = arr[left--]
        res[start++] = arr[right++]
    }

    println(res.joinToString(" "))
}