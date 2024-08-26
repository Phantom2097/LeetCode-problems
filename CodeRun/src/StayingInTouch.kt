import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

data class Point(val x: Int, val y: Int)

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val maxSize = 1001
    val map = Array(maxSize) { BooleanArray(maxSize) }

    val n = reader.readLine().toInt()

    for (i in 0 ..< n) {
        val (x, y, dist) = reader.readLine().split(" ").map { it.toInt() }

        for (j in maxOf(0, x - dist)..minOf(maxSize - 1, x + dist)) {
            for (k in maxOf(0, y - dist)..minOf(maxSize - 1, y + dist)) {
                map[j][k] = true
            }
        }
    }

    val (xStart, yStart) = reader.readLine().split(" ").map { it.toInt() }

    val (xFin, yFin) = reader.readLine().split(" ").map { it.toInt() }

    if (!map[xStart][yStart] || !map[xFin][yFin]) {
        writer.write("0\n")
        writer.flush()
        return
    }

    val directions = arrayOf(
        Point(1, 0),
        Point(-1, 0),
        Point(0, 1),
        Point(0, -1),
        Point(1, 1),
        Point(-1, -1),
        Point(1, -1),
        Point(-1, 1))

    val queue: Queue<Point> = LinkedList()
    val visited = Array(maxSize) { BooleanArray(maxSize) }

    queue.add(Point(xStart, yStart))
    visited[xStart][yStart] = true

    var found = false
    while (queue.isNotEmpty()) {
        val current = queue.poll()

        if (current.x == xFin && current.y == yFin) {
            found = true
            break
        }

        for (dir in directions) {
            val nowX = current.x + dir.x
            val nowY = current.y + dir.y

            if (nowX in 0..< maxSize
                && nowY in 0..< maxSize
                && map[nowX][nowY]
                && !visited[nowX][nowY])
            {
                visited[nowX][nowY] = true
                queue.add(Point(nowX, nowY))
            }
        }
    }

    if (found) {
        writer.write("1\n")
    } else {
        writer.write("0\n")
    }

    reader.close()
    writer.close()
}
