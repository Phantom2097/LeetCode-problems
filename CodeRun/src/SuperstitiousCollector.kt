import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs


private fun sumNums(num: Int): Int {
    return num / 100 + (num / 10) % 10 + num % 10
}

private fun isLucky(num: Int): Boolean {
    val leftHalf = num / 1000
    val rightHalf = num % 1000
    return sumNums(leftHalf) == sumNums(rightHalf)
}

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val num = reader.readLine().toInt()
    var numPlus = num
    var numMinus = num
    do {
        numPlus++
    } while (!isLucky(numPlus) && numPlus < 1000000)
    do {
        numMinus--
    } while (!isLucky(numMinus) && numMinus > 999999)

    var ans = ""
    ans = if (isLucky(numMinus)) {
        if (isLucky(numPlus)) {
            minOf(abs(num - numMinus), abs(numPlus - num)).toString()
        } else {
            numMinus.toString()
        }
    } else {
        numPlus.toString()
    }

    writer.write(ans)

    reader.close()
    writer.close()
}