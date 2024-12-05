package algo6_0.dz4

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    fun factmod(n: Int, p: Int): Int {
        var nVar = n
        var res = 1
        while (nVar > 1) {
            res = (res * if ((nVar / p) % 2 == 1) p - 1 else 1) % p
            for (i in 2..(nVar % p)) {
                res = (res * i) % p
            }
            nVar /= p
        }
        return res % p
    }

    val (n, m, k) = reader.readLine().trim().split(" ").map { it.toInt() }

    val woodFriends = mutableMapOf<Int, MutableList<Int>>()

    repeat(m) {
        val (first, second) = reader.readLine().trim().split(" ").map { it.toInt() - 1}

        val firstNode = woodFriends.getOrPut(first) { mutableListOf() }
        val secondNode = woodFriends.getOrPut(second) { mutableListOf() }

        firstNode.add(second)
        secondNode.add(first)
    }

    val isMovedIn = BooleanArray(n)

    fun dfs(root: Int, prev: Int): Long {
        if (isMovedIn[root]) return 0L
        isMovedIn[root] = true
        var thisMoves = 1L
        var thisCount = 0
        var haveFriendToo = 0
        for (friend in woodFriends[root]!!) {
            if (friend == prev) continue
            thisCount++
            var temp = dfs(friend, root)
            if (temp > 1) {
                haveFriendToo++
                if (haveFriendToo > 2) {
                    thisMoves = 0
                    break
                }
            }
            thisMoves = (thisMoves * (temp % k)) %k
        }
        if (thisCount - haveFriendToo > 0) {
            thisMoves = (thisMoves * ((thisCount - haveFriendToo) % k)) % k
        }
        if (root == prev && thisCount == 1 && woodFriends[root]!!.size != 1) thisMoves = (thisMoves * 2) % k
        return thisMoves
    }

    var count = 0
    var result = 1L

    for ((root, _) in woodFriends) {
        if (!isMovedIn[root] && result != 0L) {
            count++
            result = (result * (dfs(root, root) % k)) % k
            result = (result * (count % k) * 2) % k // ????????????????????????? куда деть 2?
        }
    }

    if (result != 0L) {
        var thisCount = 0
        isMovedIn.forEach {
            if (!it && result != 0L) {
                result = (result * ((result + ++thisCount) % k)) % k
//                count++
            }
        }
    }

    result = (result * (factmod(count, k))) % k

    writer.write("$result\n")

    reader.close()
    writer.close()
}