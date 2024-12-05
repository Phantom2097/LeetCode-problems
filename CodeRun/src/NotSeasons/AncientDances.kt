package NotSeasons

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val line = reader.readLine()!!
    var maxRight = 0
    var move = 0
    var minLeft = 0
    var emptyMove = 0

    line.forEach {
        when(it) {
            'R' -> move++
            'L' -> move--
            else -> emptyMove++
        }
        maxRight = maxOf(maxRight, move + emptyMove)
        minLeft = minOf(minLeft, move - emptyMove)
    }

    writer.write("${abs(maxRight + minLeft) + emptyMove}")

    reader.close()
    writer.close()
}