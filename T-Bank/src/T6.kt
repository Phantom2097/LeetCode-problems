import java.util.*


fun findMinTime(n: Int, processData: List<List<Int>>): Int {
    val graph = Array(n + 1) { mutableListOf<Int>() }
    val indegree = IntArray(n + 1)
    val execTime = IntArray(n + 1)

    for (i in processData.indices) {
        val data = processData[i]
        val processIndex = i + 1
        execTime[processIndex] = data[0]
        for (dep in data.drop(1)) {
            graph[dep].add(processIndex)
            indegree[processIndex]++
        }
    }

    val topoOrder = mutableListOf<Int>()
    val queue: Queue<Int> = LinkedList()
    val dp = IntArray(n + 1)

    for (i in 1..n) {
        if (indegree[i] == 0) {
            queue.add(i)
            dp[i] = execTime[i]
        }
    }

    while (queue.isNotEmpty()) {
        val process = queue.poll()
        topoOrder.add(process)

        for (dependent in graph[process]) {
            indegree[dependent]--
            dp[dependent] = maxOf(dp[dependent], dp[process] + execTime[dependent])
            if (indegree[dependent] == 0) {
                queue.add(dependent)
            }
        }
    }
    return dp.maxOrNull() ?: 0
}

fun main() {
    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()
    scanner.nextLine()

    val processData = mutableListOf<List<Int>>()
    repeat(n) {
        val data = scanner.nextLine().split(" ").map { it.toInt() }
        processData.add(data)
    }

    println(findMinTime(n, processData))
}
