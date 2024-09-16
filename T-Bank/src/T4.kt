import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.sqrt

fun sieveOfEratosthenes(n: Int): List<Int> {
    val isPrime = BooleanArray(n + 1) { true }
    isPrime[0] = false
    isPrime[1] = false

    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (isPrime[i]) {
            for (j in i * i.. n step i) {
                isPrime[j] = false
            }
        }
    }
    return isPrime.indices.filter { isPrime[it] }
}

fun countDivisors(n: Long): Int {
    var count = 0
    for (i in 1..sqrt(n.toDouble()).toLong()) {
        if (n % i == 0L) {
            count++
            if (i != (n / i)) {
                count++
            }
        }
    }
    return count
}

fun simpleNumbers(): Int {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val (l, r) = reader.readLine().split(" ").map { it.toLong() }

    var count = 0

    val primes = sieveOfEratosthenes(minOf(350, sqrt(r.toDouble()).toInt() + 1)).toSet()

    for (num in l .. r) {
        if (num == 1L) continue
        val divisors = countDivisors(num)
        if (divisors > 2 && divisors in primes) {
            count++

        }
    }
    return count
}


fun main() {
    println(simpleNumbers())
}