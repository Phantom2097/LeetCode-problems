package algo6_0.dz4

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    class TreeNode(val name: String, var parent: String? = null) {
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

            childNode.parent = parent

            parentNodes.children.add(childNode)

            children.add(child)
        }
        val rootValue = map.keys.first { it !in children }

        var line = reader.readLine()
        while (line != null && line.isNotBlank()) {
            val (first, second) = line.trim().split(" ")
            if (first == rootValue || second == rootValue) {
                writer.write("$rootValue\n")
            } else {
                fun pathToRoot(name: String): List<String> {
                    val path = mutableListOf<String>()
                    var currentName: String? = name
                    while (currentName != null) {
                        path.add(currentName)
                        currentName = map[currentName]?.parent
                    }
                    return path
                }
                val firstPath = pathToRoot(first)
                val secondPath = pathToRoot(second)

                if (firstPath.size > secondPath.size) {
                    writer.write(firstPath.first { it in secondPath} + "\n")
                } else {
                    writer.write(secondPath.first { it in firstPath} + "\n")
                }
            }
            line = reader.readLine()
        }
    }

    reader.close()
    writer.close()
}