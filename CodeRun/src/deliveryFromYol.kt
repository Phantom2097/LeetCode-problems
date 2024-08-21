import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val stringWay = reader.readLine().toString()
     val way = stringWay.map {it.code - 48}
    // val three = stringWay.length - way.size // Количество дорог с обеих сторон

    var transitons = 0 // Количество переходов
    var pos = false // true - внизу, false - сверху

    var count = 0 // Количество дорог с одной стороны
    var prev = 0 // Прошлый элемент


    for (w in way) {
        when (w) {
            3 -> {
                transitons++
            }
            prev -> {
                count++
            }
            else -> {
                if (pos) {
                    if (prev == 2) {
                        if (count >= 2) {
                            transitons++
                            pos = false
                        } else {
                            transitons += count
                        }
                    }
                    count = 1
                } else {
                    if (prev == 1) {
                        if (count > 1) {
                            pos = true
                            transitons++
                        } else {
                            transitons++
                        }
                    }
                    count = 1
                }
                prev = w
            }
        }
    }

    if (pos) {
        if (prev == 2) {
            transitons += minOf(count, 2)
        }
    } else {
        transitons++
    }

    writer.write((transitons).toString())

    reader.close()
    writer.close()
}
