fun countUnguarded(m: Int, n: Int, guards: Array<IntArray>, walls: Array<IntArray>): Int {
    val set = mutableSetOf<IntArray>()

    fun guardedCells(i: Int, j: Int) {
        val right = intArrayOf(i, j + 1)
        while(right[1] < n && right !in walls) {
            set.add(right)
            right[1]++
        }
        val left = intArrayOf(i, j - 1)
        while(left[1] >= 0 && left !in walls) {
            set.add(left)
            left[1]--
        }
        val up = intArrayOf(i + 1, j)
        while(up[0] < m && up !in walls) {
            set.add(up)
            up[0]++
        }
        val down = intArrayOf(i - 1, j)
        while(down[0] >= 0 && down !in walls) {
            set.add(down)
            down[0]--
        }


    }
    guards.forEach { it ->
        set.add(it)
        guardedCells(it[0], it[1])
    }

    return m * n - set.size - walls.size
}

fun main() {
    print(countUnguarded(
        4,
        6,
        arrayOf(intArrayOf(0,0), intArrayOf(1,1), intArrayOf(2,3)),
        arrayOf(intArrayOf(0,1), intArrayOf(2,2), intArrayOf(1,4))
    ))
}