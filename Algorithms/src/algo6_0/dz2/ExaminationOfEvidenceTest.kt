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
//    val n = readln().toInt()
//
//    val weightiness = readln().split(" ").map { it.toInt() }.toIntArray()
//
//    val (m, k) = readln().split(" ").map { it.toInt() }
//
//    val experiments = readln().split(" ").map { it.toInt() }

    val n = Random.nextInt(1, 400_001)
    val weightiness = IntArray(n) { Random.nextInt(1, 1_000_000_001) }
    val m = Random.nextInt(1, 400_001)
    val k = Random.nextInt(1, n + 1)
    val experiments = IntArray(m) { Random.nextInt(1, n + 1) }

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
    val trueRes = findIndex(experiments, weightiness, m, k)
    var isOk = true
    var iterations = 0L
    while (isOk) {
        if (!result.contentEquals(trueRes)) isOk = false
        println("$isOk ${iterations++}")
        println(findIndex(experiments, weightiness, m, k).joinToString(" "))
        println(result.joinToString(" "))
    }
    println(findIndex(experiments, weightiness, m, k).joinToString(" "))
    println(result.joinToString(" "))
}