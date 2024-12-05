package NotSeasons

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine()!!.toInt()

//    val pawns = mutableListOf<Pair<Int, Int>>() // координаты пешек тута
    // теперь тута
    val rowMap = mutableMapOf<Int, MutableSet<Int>>()
    val colMap = mutableMapOf<Int, MutableSet<Int>>()
    val diag1Map = mutableMapOf<Int, MutableSet<Int>>()
    val diag2Map = mutableMapOf<Int, MutableSet<Int>>()

    repeat(n) {
        val (x, y) = reader.readLine()!!.split(" ").map { it.toInt() }

        rowMap.computeIfAbsent(x) { mutableSetOf() }.add(y)
        colMap.computeIfAbsent(y) { mutableSetOf() }.add(x)
        diag1Map.computeIfAbsent(x - y) { mutableSetOf() }.add(x)
        diag2Map.computeIfAbsent(x + y) { mutableSetOf() }.add(x)
//        pawns.add(Pair(x, y))
    }

    val q = reader.readLine()!!.toInt()


    repeat(q) {
        val (figure, strX, strY) = reader.readLine()!!.split(" ")
        val x = strX.toInt()
        val y = strY.toInt()

        var count = 0

        when (figure) {
            "P" -> {
//                if (pawns.contains(Pair(x + 1, y + 1))) count++
//                if (pawns.contains(Pair(x - 1, y + 1))) count++
                if (rowMap[x - 1]?.contains(y + 1) == true) count++
                if (rowMap[x + 1]?.contains(y + 1) == true) count++
            }
            "K" -> {
                val moves = listOf(
                    Pair(- 2, 1), Pair( - 2, - 1), Pair(- 1, 2), Pair(- 1, - 2),
                    Pair(2, 1), Pair(2, - 1), Pair(1, 2), Pair(1, - 2)
                )
                for (move in moves) {
                    if (rowMap[x + move.first]?.contains(y + move.second) == true) count++
                }
            }
            "Q" -> {
                count += rowMap[x]?.size ?: 0
                count += colMap[y]?.size ?: 0
                count += diag1Map[x - y]?.size ?: 0
                count += diag2Map[x + y]?.size ?: 0
            }
            "R" -> {
                count += rowMap[x]?.size ?: 0
                count += colMap[y]?.size ?: 0
            }
            "B" -> {
                count += diag1Map[x - y]?.size ?: 0
                count += diag2Map[x + y]?.size ?: 0
            }
        }
        writer.write("$count\n")
    }

    reader.close()
    writer.close()
}