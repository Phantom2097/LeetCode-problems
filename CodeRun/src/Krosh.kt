import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

fun canReachWithLevel(level: Int, n: Int, k: Int, a: IntArray): Boolean {
    val visited = BooleanArray(n)
    val queue: Queue<Int> = LinkedList()

    queue.add(0)
    visited[0] = true

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        for (jump in 1..k) {
            val nextPosForward = current + jump
            val nextPosBackward = current - jump

            if (nextPosForward < n && !visited[nextPosForward] && a[nextPosForward] <= level) {
                if (nextPosForward == n - 1) return true
                visited[nextPosForward] = true
                queue.add(nextPosForward)
            }

            if (nextPosBackward >= 0 && !visited[nextPosBackward] && a[nextPosBackward] <= level) {
                visited[nextPosBackward] = true
                queue.add(nextPosBackward)
            }
        }
    }

    return false
}
fun minimumLevelForPath(n: Int, k: Int, a: IntArray): Int {
    var low = max(a[0], a[n - 1])
    var high = a.maxOrNull() ?: low

    while (low < high) {
        val mid = (low + high) / 2
        if (canReachWithLevel(mid, n, k, a)) {
            high = mid
        } else {
            low = mid + 1
        }
    }

    return low
}

fun main() {
    val input = readLine()!!.split(" ").map { it.toInt() }
    val n = input[0]
    val k = input[1]
    val a = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    println(minimumLevelForPath(n, k, a))
}
