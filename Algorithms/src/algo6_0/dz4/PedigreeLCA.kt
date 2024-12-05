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
                fun findChild(currentName: String, childNameBefore: String?, findingName: String): Boolean {
                    var isThisName = false
                    if (currentName == findingName) return true
                    val currentChildren = map[currentName]!!.children
                    if (currentChildren.isNotEmpty()) {
                        for (child in currentChildren) {
                            if (!childNameBefore.isNullOrEmpty() && childNameBefore == child.name) continue
                            isThisName = if (child.name == findingName) {
                                true
                            } else {
                                findChild(child.name, childNameBefore, findingName)
                            }
                            if (isThisName) break
                        }
                    }
                    return isThisName
                }
                fun findParent(currentName: String, childNameBefore: String?, findingName: String): String {
                    var thisName = currentName
                    if (currentName == findingName) return thisName
                    if (currentName == rootValue) return rootValue
                    else if (!findChild(currentName, childNameBefore, findingName)) {
                        thisName = findParent(map[currentName]!!.parent!!, currentName, findingName)
                    }
                    return thisName
                }
                writer.write(findParent(first, null, second) + "\n")

            }
            line = reader.readLine()
        }
    }

    reader.close()
    writer.close()
}