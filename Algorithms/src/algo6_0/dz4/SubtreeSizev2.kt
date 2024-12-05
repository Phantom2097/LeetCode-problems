package algo6_0.dz4

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
//    class TreeNode(var `val`: Int) {
//        val children = mutableListOf<TreeNode>()
//    }
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().trim().toInt()

    val allNodes = Array(n + 1) { mutableListOf<Int>() }


    repeat(n - 1) {
        val (first, second) = reader.readLine().trim().split(" ").map { it.toInt() }
        allNodes[first].add(second)
        allNodes[second].add(first)
    }

    val result = IntArray(n)
    val isDone = BooleanArray(n + 1)

    fun treeSize(root: Int): Int {
        var childrenCount = 1
        isDone[root] = true
        if (allNodes[root].size != 0) {
            for (child in allNodes[root]) {
                if (!isDone[child]) {
                    childrenCount += treeSize(child)
                }
            }
        }
        result[root - 1] = childrenCount
        return childrenCount
    }
    treeSize(1)
    writer.write(result.joinToString(" ", postfix = "\n"))

    reader.close()
    writer.close()
}