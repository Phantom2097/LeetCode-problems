package algo6_0.dz4

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().trim().toInt()
    val subordination = reader.readLine().trim().split(" ").map { it.toInt() - 1 }

    val children = subordination.withIndex().groupBy({ it.value }, { it.index + 1 })

    val resultMoney = LongArray(n) { 1 }
    val childrenCount = IntArray(n) { 0 }

    fun dfs(root: Int): Long {
        var money = 0L
        if (root in children.keys) {
            for (child in children[root]!!) {
                money += dfs(child)
                childrenCount[root] += childrenCount[child] + 1
            }
        }
        resultMoney[root] += money + childrenCount[root]
        return resultMoney[root]
    }

    dfs(0)

    writer.write(resultMoney.joinToString(" ", postfix = "\n"))

    reader.close()
    writer.close()
}