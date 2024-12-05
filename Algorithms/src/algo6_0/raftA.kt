package algo6_0

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val x1 = reader.readLine().toByte()
    val y1 = reader.readLine().toByte()

    val x2 = reader.readLine().toByte()
    val y2 = reader.readLine().toByte()

    val x = reader.readLine().toByte()
    val y = reader.readLine().toByte()

//    val tests = listOf(
//        byteArrayOf(-1, -1, 1, 1, 2, 1), // NE
//        byteArrayOf(-1, -1, 1, 1, 2, 3), // NE
//        byteArrayOf( -1, -2, 5, 3, -4, 6), // NW
//        byteArrayOf(-100, -100, 100, 99, 100, 100), // NE
//        byteArrayOf(-1, -1, 1, 1, 0, 6),
//        byteArrayOf(-1, -1, 1, 1, 6, 0),
//        byteArrayOf(-1, -1, 1, 1, 0, -6),
//        byteArrayOf(-1, -1, 1, 1, -6, 0),
//        byteArrayOf(-1, -1, 1, 1, -2, 1),
//        byteArrayOf(-1, -1, 1, 1, -2, -1),
//        byteArrayOf(-1, -1, 1, 1, 2, -1),
//        byteArrayOf(-1, -1, 1, 1, 2, 1),
//        byteArrayOf(-1, -1, 1, 1, -2, 2),
//        byteArrayOf(-1, -1, 1, 1, -2, -2),
//        byteArrayOf(-1, -1, 1, 1, 2, -2),
//        byteArrayOf(-1, -1, 1, 1, 2, 2),
//        byteArrayOf(-1, -1, 1, 1, -1, 2),
//        byteArrayOf(-1, -1, 1, 1, -1, -2),
//        byteArrayOf(-1, -1, 1, 1, 1, -2),
//        byteArrayOf(-1, -1, 1, 1, 1, 2),
//    )
//    for (test in tests) {
//        println(raft(test[0], test[1], test[2], test[3], test[4], test[5]))
//    }
    writer.write(raft(x1, y1, x2, y2, x, y))

    reader.close()
    writer.close()
}

private fun raft(x1: Byte, y1: Byte, x2: Byte, y2: Byte, x: Byte, y: Byte): String {
    return when {
        x >= x2 -> {
            when {
                y >= y2 -> {
                    "NE"
                }
                y <= y1 -> {
                    "SE"
                }
                else -> "E"
            }
        }
        x <= x1 -> {
            when {
                y >= y2 -> {
                    "NW"
                }
                y <= y1 -> {
                    "SW"
                }
                else -> "W"
            }
        }
        else -> {
            if (y > y2) "N" else "S"
        }
    }
}

