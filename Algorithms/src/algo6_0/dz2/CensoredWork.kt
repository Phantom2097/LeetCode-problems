package algo6_0.dz2

fun main() {
    val (n, c) = readln().split(" ").map { it.toLong() }
    val str = readln()

    var maxLen = 0
    var len = 0
    var left = 0
    var a = 0
    var b = 0
    var coarseness = 0L

    for (i in str.indices) {
        len++
        if (str[i] == 'a') a++
        if (str[i] == 'b') {
            b++
            coarseness += a
        }
        while (coarseness > c) {
            val temp = str[left]
            if (temp == 'a') {
                a--
                coarseness -= b
            }
            if (temp == 'b') b--
            left++
            len--
        }
        maxLen = maxOf(len, maxLen)
    }
    print(maxLen)
}