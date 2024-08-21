import kotlin.math.abs

fun smallestDistancePair(nums: IntArray, k: Int): Int {
    val sum = ArrayList<Int>()
    for (i in nums.indices) {
        for (j in i + 1 .. nums.lastIndex) {
            sum.add(abs(nums[i] - nums[j]))
        }
    }
    val sortedSum = sum.sorted()
    return sortedSum[k - 1]
}
