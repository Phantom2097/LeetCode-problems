package algo6_0.dz4

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    class TreeNode(var `val`: Int, val id: Int) {
        val children = mutableListOf<TreeNode>()
    }
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().trim().toInt()
    var count = 0
    val nodes = mutableMapOf<Int, TreeNode>()
    nodes[1] = TreeNode(1, count++)

    val allNodes = mutableListOf<Int>(1)

    val notAdded = mutableListOf<Pair<Int, Int>>()

    repeat(n - 1) {
        val (first, second) = reader.readLine().trim().split(" ").map { it.toInt() }
//        val firstNode = nodes.getOrPut(first) { TreeNode(first, 0) }
//        val secondNode = nodes.getOrPut(second) { TreeNode(second, 0) }

        if (first in allNodes) {
            allNodes.add(second)
            val secondNode = TreeNode(second, count++)
            nodes[first]!!.children.add(secondNode)
            nodes[second] = secondNode
        } else if (second in allNodes) {
            allNodes.add(first)
            val firstNode = TreeNode(first, count++)
            nodes[second]!!.children.add(firstNode)
            nodes[first] = firstNode
        } else {
            notAdded.add(first to second)
        }
    }
    var idx = 0
    while (notAdded.isNotEmpty()) {
        val temp = notAdded[idx]
        if (temp.first in allNodes) {
            allNodes.add(temp.second)
            val secondNode = TreeNode(temp.second, count++)
            nodes[temp.first]!!.children.add(secondNode)
            nodes[temp.second] = secondNode
            notAdded.removeAt(idx)
        } else if (temp.second in allNodes) {
            allNodes.add(temp.first)
            val firstNode = TreeNode(temp.first, count++)
            nodes[temp.second]!!.children.add(firstNode)
            nodes[temp.first] = firstNode
            notAdded.removeAt(idx)
        } else {
            idx++
        }
        if (idx == notAdded.size) {
            idx = 0
        }
    }

    val result = IntArray(n)

    fun treeSize(root: TreeNode): Int {
        var childrenCount = 1
        if (root.children.size != 0) {
            for (child in root.children) {
                childrenCount += treeSize(child)
            }
        }
        result[root.`val` - 1] = childrenCount
        return childrenCount
    }
    treeSize(nodes[1]!!)
    writer.write(result.joinToString(" ", postfix = "\n"))

    reader.close()
    writer.close()
}