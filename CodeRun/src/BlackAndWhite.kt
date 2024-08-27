import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/*
    Будто бы за единицу площади считаем то, что находится между двумя элементами, может прокатит
 */

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))


    val (n, m) = reader.readLine().split(" ").map { it.toInt() }

    val grid = Array(n) { reader.readLine().toCharArray() }

    var totalArea = 0

    for (i in 0 ..< n) {
        var inside = false
        var lineArea = 0

        for (j in 0 ..< m) {
            when (grid[i][j]) {
                '/' -> inside = !inside
                '\\' -> inside = !inside
            }
            if (inside) {
                lineArea++
            }
        }
        totalArea += lineArea
    }

    writer.write(totalArea.toString())

    reader.close()
    writer.close()
}
