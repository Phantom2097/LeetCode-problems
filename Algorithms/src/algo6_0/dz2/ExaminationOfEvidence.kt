package algo6_0.dz2

import kotlin.random.Random

/*
8
1 3 2 4 5 6 8 7
8 0
1 2 3 4 5 6 7 8
должно быть
1 1 3 3 3 3 3 8
 */
fun main() {
    val n = readln().toInt()

    val weightiness = readln().split(" ").map { it.toInt() }.toIntArray()

    val (m, k) = readln().split(" ").map { it.toInt() }

    val experiments = readln().split(" ").map { it.toInt() }

    val exPos = IntArray(n)
    var left = 0
    var fine = 0
    /*
    Проход от начала до конца, хранить в очереди возможные индексы, на которых останавливается, если список больше длины,
    то удаляем первый, дальше записываем новый первый. Если элемент, меньше предыдущего, то очищаем очередь и заполняем заново.
     */
    for (i in 1 ..< n) {
        if (weightiness[i] >= weightiness[i - 1]) {
            if (weightiness[i] == weightiness[i - 1]) {
                fine++
                while (fine > k) {
                    if (weightiness[left] == weightiness[++left]) fine--
                }
            }
        } else {
            left = i
            fine = 0
        }
        exPos[i] = left
    }
    val result = IntArray(m)

    experiments.forEachIndexed { index, ex ->
        result[index] = exPos[ex - 1] + 1
    }

    println(result.joinToString(" "))
}