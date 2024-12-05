class Solution {
    fun minEnd(n: Int, x: Int): Long {
        var value = x.toString(2).reversed()

        val result = StringBuilder()
        var count = (n - 1).toString(2).reversed()
        var idx = 0
        var i = 0

        while (i + idx < value.length + count.length + 1) {
            if (i < value.length) {
                if (value[i] == '0') {
                    result.append(count[idx].toString())
                    idx++
                } else {
                    result.append(value[i].toString())
                }
            } else if (idx < count.length) {
                result.append(count[idx].toString())
                idx++
            }
            i++
        }
        return result.toString().reversed().toLong(2)
    }
}

//class Main {
    fun main() {
        println(Solution().minEnd(3, 1))
    }
//}