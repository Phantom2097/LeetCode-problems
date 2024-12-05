package algo6_0.dz4

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {

    class BinaryTree(var `val`: Int) {
        var left: BinaryTree? = null
        var right: BinaryTree? = null
    }

    fun search(root: BinaryTree?, searchValue: Int): BinaryTree {
        var result = root
        if (root!!.`val` == searchValue) {
            return root
        } else if (root.left == null && root.right == null) {
            return root
        } else {
            if (root.`val` > searchValue) {
                root.left?.let {
                    result = search(root.left, searchValue)
                }
            } else {
                root.right?.let {
                    result = search(root.right, searchValue)
                }
            }
        }
        return result!!
    }

    fun add(root: BinaryTree?, addValue: Int): Boolean {
        val searchedValue = search(root, addValue)
        if (searchedValue.`val` == addValue) {
            return false
        } else {
            if (searchedValue.`val` > addValue) {
                searchedValue.left = BinaryTree(addValue)
            } else {
                searchedValue.right = BinaryTree(addValue)
            }
        }
        return true
    }



    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    fun printTree(root: BinaryTree, depth: Int) {
        root.left?.let {
            printTree(root.left!!, depth + 1)
        }
        writer.write(".".repeat(depth) + root.`val` + "\n")
        root.right?.let {
            printTree(root.right!!, depth + 1)
        }
    }

    var line = reader.readLine()
    var root: BinaryTree? = null
    while (!line.isNullOrBlank()) {
        val operations = line.trim().split(" ")
        when (operations[0]) {
            "ADD" -> {
                if (root == null) {
                    root = BinaryTree(operations[1].toInt())
                    writer.write("DONE\n")
                } else {
                    val value = operations[1].toInt()
                    if (add(root, value)) {
                        writer.write("DONE\n")
                    } else {
                        writer.write("ALREADY\n")
                    }
                }
            }
            "SEARCH" -> {
                if (root == null) {
                    writer.write("NO\n")
                } else if (search(root, operations[1].toInt()).`val` == operations[1].toInt()) {
                    writer.write("YES\n")
                } else {
                    writer.write("NO\n")
                }
            }
            else -> {
                printTree(root!!, 0)
            }
        }
        line = reader.readLine()
    }

    reader.close()
    writer.close()
}