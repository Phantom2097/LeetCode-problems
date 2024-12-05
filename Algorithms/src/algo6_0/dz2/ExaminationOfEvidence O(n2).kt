package algo6_0.dz2

fun main() {
    val n = readln().toInt()

    val weightiness = readln().split(" ").map { it.toInt() }.toIntArray()

    val (m, k) = readln().split(" ").map { it.toInt() }

    val experiments = readln().split(" ").map { it.toInt() }

    val result = findIndex(experiments.toIntArray(), weightiness, m, k)

    println(result.joinToString(" "))
}

fun findIndex(experiments: IntArray, weightiness: IntArray, m: Int, k: Int): IntArray {
    val result = IntArray(m)

    experiments.forEachIndexed { i, ex ->
        var idx = ex - 1
        var fine = 0
        while (idx > 0 && weightiness[idx] >= weightiness[idx - 1] && fine <= k ) {
            if (weightiness[idx] == weightiness[idx - 1]) {
                if (fine >= k) break
                fine++
            }
            idx--
        }
        result[i] = idx + 1
    }
    return result
}

