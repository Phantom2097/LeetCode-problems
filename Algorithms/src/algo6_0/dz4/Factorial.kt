package algo6_0.dz4

fun main() {
    val n = 5
    val p = 13
    var count = 1L
    repeat(n) {
        val now = it % p + 1
        count = (count * now) % p
        if (count == 0L) count++
    }
    println(count)
    println(3_628_800 % p)
//    println(factmo(9, p))
}

