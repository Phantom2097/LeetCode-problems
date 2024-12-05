package algo6_0.dz4

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().trim().toInt()
//    var idx = 2
    val subordination = reader.readLine().trim().split(" ").map { it.toInt() }.withIndex().groupBy ({ it.value - 1}, { it.index + 1 })

    val quit = BooleanArray(n)

    val resultMoney = IntArray(n)

    fun moneyForWorker(i: Int): Int {
        var money = 0
        if (!quit[i]) {
            subordination[i]?.let {
                for (num in it) {
                    if (!quit[num]) {
                        money += moneyForWorker(num)
                        break
                    }
                }
            }
            if (money == 0) {
                quit[i] = true
            }
            money++
        }
        resultMoney[i] += money
        return money
    }
    while (!quit[0]) {
        moneyForWorker(0)
    }

    writer.write(resultMoney.joinToString(" ", postfix = "\n"))


    writer.close()
    reader.close()
}