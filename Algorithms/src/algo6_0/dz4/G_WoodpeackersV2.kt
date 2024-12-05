package algo6_0.dz4

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/*
    Для каждого поддерева считать зеркальность сразу, кроме одиночных
    посчитать количество расположений деревьев относительно друг друга - факториал их количества
    и каждый раз умножать на количество возможных вариантов этого дерева из двух дятлов на 2, из 3ёх на 4 итд.

    Для оставшихся одиночных, количество (дятлов + 1) * количество перестановок
 */

//fun main() {
//    val reader = BufferedReader(InputStreamReader(System.`in`))
//    val writer = BufferedWriter(OutputStreamWriter(System.out))
//
//    val (n, m, k) = reader.readLine().trim().split(" ").map { it.toInt() }
//
//    val woodFriends = mutableMapOf<Int, MutableList<Int>>()
//
//    repeat(m) {
//        val (first, second) = reader.readLine().trim().split(" ").map { it.toInt() - 1 }
//
//        val firstNode = woodFriends.getOrPut(first) { mutableListOf() }
//        val secondNode = woodFriends.getOrPut(second) { mutableListOf() }
//
//        firstNode.add(second)
//        secondNode.add(first)
//    }
//
//    fun factmod(n: Int): Int {
//        var nVar = n
//        var res = 1
//        while (nVar > 1) {
//            res = (res * if ((nVar / k) % 2 == 1) k - 1 else 1) % k
//            for (i in 2..(nVar % k)) {
//                res = (res * i) % k
//            }
//            nVar /= k
//        }
//        return res % k
//    }
//
//    val isMovedIn = BooleanArray(n)
//    var result = 1L
//
//    var countOfTrees = 0
//    var countWoodpeckers = 0
//
//    fun dfs(root: Int, prev: Int): Int {
//        if (isMovedIn[root]) {
//            result *= 0L
//            return 1
//        }
//        isMovedIn[root] = true
//        countWoodpeckers++
////        var moves = 1
//        var friendCount = 0
//        var friendWithOtherFriends = 0
//        for (friend in woodFriends[root]!!) {
//            if (friend == prev) continue
//            friendCount++
//            val temp = dfs(friend, root)
//            if (temp > 0) {
//                friendWithOtherFriends++
//                if (root != prev && friendWithOtherFriends > 1 || root == prev && friendWithOtherFriends > 2) {
//                    result *= 0L
//                }
//            }
//        }
//        val f = friendCount - friendWithOtherFriends
//        if (f > 1) {
//            result *= factmod(friendCount - friendWithOtherFriends)
//        }
//        if (root == prev && friendWithOtherFriends > 0) result *= (result * 2) % k
//
//        return friendCount
//    }
//
//    for (root in woodFriends.keys) {
//        if (!isMovedIn[root]) {
//            dfs(root, root)
//            if (result == 0L) break
//            result = (result * 2) % k
//            countOfTrees++
//        }
//    }
////    if (result != 0L) {
////        repeat(countOfTrees) {
////            result = (result * 2) % k
////        }
////    }
//
//
//
//    if (result > 0 && countOfTrees > 1) {
//        result = (result * (factmod(countOfTrees))) % k
//    }
//
//    if (result != 0L) {
//        while (countWoodpeckers < n) {
//            result = (result * ((countWoodpeckers + 2) % k)) % k
//            countWoodpeckers++
//        }
//    }
//
//    writer.write("$result\n")
//
//    writer.close()
//    reader.close()
//}