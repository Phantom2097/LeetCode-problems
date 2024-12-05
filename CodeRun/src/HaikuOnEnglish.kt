import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val s = reader.readLine()
    val n = s.length

    val yandex = "Yandex"
    val cup = "Cup"

    if (n == 9) {
        writer.write("YandexCup")
    } else {
        var bestCupPos = -1
        var minCupChanges = Int.MAX_VALUE

        for (i in 0..n - cup.length) {
            var changes = 0
            for (j in cup.indices) {
                if (s[i + j] != cup[j]) changes++
            }
            if (changes < minCupChanges) {
                minCupChanges = changes
                bestCupPos = i
            }
        }

        var bestYandexPos = -1
        var minYandexChanges = Int.MAX_VALUE

        for (i in 0..bestCupPos - yandex.length) {
            var changes = 0
            for (j in yandex.indices) {
                if (s[i + j] != yandex[j]) changes++
            }
            if (changes < minYandexChanges) {
                minYandexChanges = changes
                bestYandexPos = i
            }
        }

        val result = StringBuilder(s)
        if (bestYandexPos != -1) {
            for (j in yandex.indices) {
                result[bestYandexPos + j] = yandex[j]
            }
        }

        for (j in cup.indices) {
            result[bestCupPos + j] = cup[j]
        }

        writer.write(result.toString())
    }

    reader.close()
    writer.close()
}
