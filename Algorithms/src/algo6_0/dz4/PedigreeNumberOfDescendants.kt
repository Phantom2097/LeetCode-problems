package algo6_0.dz4

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    class TreeNode(val parent: String) {
        val children = mutableListOf<TreeNode>()
    }
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().trim().toInt()

    if (n == 1) {
        writer.write(reader.readLine().trim() + " 0")
    } else {
        val map = mutableMapOf<String, TreeNode>()

        val children = mutableSetOf<String>()

        repeat(n - 1) {
            val (child, parent) = reader.readLine().trim().split(" ")

            val parentNodes = map.getOrPut(parent) { TreeNode(parent) }

            val childNode = map.getOrPut(child) { TreeNode(child) }

            parentNodes.children.add(childNode)

            children.add(child)
        }
        val rootValue = map.keys.first { it !in children }

        val lvlList = mutableListOf<String>()
        //        lvlList.add("$rootValue 0")
        fun lvl(node: TreeNode, lvl: Int): Int {
            var count = 0

            if (node.children.isNotEmpty()) {
                for (child in node.children) {
                    count += lvl(child, lvl + 1) + 1
                }
            }
            lvlList.add("${node.parent} $count")
            return count
        }
        lvl(map[rootValue]!!, 0)

        writer.write(lvlList.sorted().joinToString(separator = "\n", postfix = "\n"))
    }

    reader.close()
    writer.close()
}

