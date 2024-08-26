import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/*
    Нужен ещё словарь, отсортированный и с изначальными позициями
 */
data class OpponentPos(val lvl: Int, val pos: Int)
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()
    val opponents = reader.readLine().split(" ").map { it.toInt() }

    val list = mutableListOf<OpponentPos>()
    for (i in opponents.indices) {
        list.add(OpponentPos(lvl = opponents[i], pos = i + 1))
    }
    list.sortBy { it.lvl }

    val answer = mutableListOf<Int>()

    var fines = 0
    var lvl = 1

    var flag = true

    for (opponent in list) {
        if (lvl >= opponent.lvl) {
            if (lvl <= opponent.lvl * opponent.lvl) {
                fines++
            } else {
                fines = 0
            }
            lvl++
            if (fines == 3) {
                lvl--
            }
            answer.add(opponent.pos)
        } else {
            flag = false
            break
        }
    }
    if (flag) {
        writer.write("Possible\n" + answer.joinToString(" "))
    } else {
        writer.write("Impossible")
    }


    reader.close()
    writer.close()
}