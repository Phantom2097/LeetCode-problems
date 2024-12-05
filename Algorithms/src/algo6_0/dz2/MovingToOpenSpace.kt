package algo6_0.dz2

fun main() {
    val n = readln().toInt()

    val offices = readln().split(" ").map { it.toInt() }

    var sumL = offices[0].toLong()
    var sumR = offices[n - 1].toLong()

    val leftSum = LongArray(n)
    val rightSum = LongArray(n)

    for (i in 1 ..< n) {
        leftSum[i] = sumL + leftSum[i - 1]
        sumL += offices[i]

        rightSum[n - i - 1] = sumR + rightSum[n - i]
        sumR += offices[n - i - 1]
    }

    var minMoves = Long.MAX_VALUE
    for (i in 0 ..< n) {
        minMoves = minOf(minMoves, leftSum[i] + rightSum[i])
    }

    println(minMoves)
//    println(leftSum.contentToString())
//    println(rightSum.contentToString())
}