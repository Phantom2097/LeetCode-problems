import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.SortedSet

fun sortingVadimSets() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val list = reader.readLine().split(",")

    val set = HashSet<Int>()

    list.forEach { l ->
        val temp = l.split("-").map { it.toInt() }
        when(temp.size) {
            1 -> set.add(temp[0])
            else -> for (i in temp[0] .. temp[1]) {
                set.add(i)
            }
        }
    }
    println(set.toSortedSet().joinToString(" "))
}

fun main(args: Array<String>) {
    sortingVadimSets()
}