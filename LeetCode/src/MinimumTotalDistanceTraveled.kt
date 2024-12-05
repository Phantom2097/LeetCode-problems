fun minimumTotalDistance(robot: List<Int>, factory: Array<IntArray>): Long {
    var ans = 0L

//    val distance = Array(robot.size) { IntArray(factory.size) { Int.MAX_VALUE } }

    var robotPos = 0


    val sortedR = robot.sorted()
    factory.sortWith(compareBy<IntArray> { it[0] } )
    val f = buildList<Int> {
        factory.forEach { el ->
            repeat(el[1]) {
                add(el[0])
            }
        }
    }

    if (f.size == robot.size) {
        return sortedR.indices.sumOf { i -> Math.abs(sortedR[i] - f[i].toLong())}
    } else {
        ans = 6L
    }

    return ans
}

fun main() {
    val robot = listOf(0, 4, 6)
    val factory = arrayOf(intArrayOf(2, 2), intArrayOf(6, 2))
    println(minimumTotalDistance(robot, factory))
}