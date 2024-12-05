package algo6_0.dz4

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {

    /*
    надо построить всё-таки дерево, чтобы проверять, есть ли дети у элементов, если есть, то для них 1 раз выполнить
    а потом чисто прибавлять для каждой ветви
     */
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().trim().toInt()
    val subordination = reader.readLine().trim().split(" ").map { it.toInt() - 1 }

    val children = subordination.withIndex().groupBy ({ it.value}, { it.index + 1})

    val resultMoney = IntArray(n) { 1 }
    val isDone = IntArray(n)

    for (i in (n - 1) downTo 1) {
        if (resultMoney[i] == 1) {
            var parent = subordination[i - 1]
            var temp = 2
            var counter = temp
            var useTemp = false
            while (parent != 0) {
                if (!useTemp && parent in children.keys) {
                    isDone[parent]++
                    if (isDone[parent] < children[parent]!!.size) useTemp = true
                }
                if (useTemp) {
                    resultMoney[parent] += counter
                    counter += temp - 1
                } else {
                    resultMoney[parent] += counter
                    temp++
                    counter += temp
                }
                parent = subordination[parent - 1]
            }
            if (!useTemp && 0 in children.keys) {
                isDone[0]++
                if (isDone[0] < children[0]!!.size) useTemp = true
            }
            if (useTemp) {
                resultMoney[0] += counter
                counter += temp - 1
            } else {
                resultMoney[0] += counter
                temp++
                counter += temp
            }
        }
    }

    writer.write(resultMoney.joinToString(" ", postfix = "\n"))

    reader.close()
    writer.close()
}
