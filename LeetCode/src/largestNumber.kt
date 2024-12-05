import kotlin.math.pow

class LargestNumber {
    fun largestNumber(nums: IntArray): String {
        val result = nums.sortedWith { a, b -> (b * (10.0.pow(a.digitLength()).toInt() - 1)).compareTo(a * (10.0.pow(b.digitLength()).toInt() - 1)) }
        return if (result[0] == 0) return "0" else result.joinToString("")
    }

//    private fun Int.digitLength(): Int {
//        var temp = this
//        var count = 0
//        if (temp == 0) return 1
//        while (temp > 0) {
//            count++
//            temp /= 10
//        }
//        return count
//    }
    private fun Int.digitLength() = this.toString().length
}