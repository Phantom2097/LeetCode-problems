import kotlin.math.abs


    public fun threeSumClosest(nums: IntArray, target: Int): Int {
        var result = Int.MAX_VALUE
        nums.sort()

        for (i in nums.indices) {
            var left = i + 1
            var right = nums.lastIndex
            while (left < right && result != target) {
                val sum = nums[i] + nums[left] + nums[right]
                when {
//                    (abs(sum - target) < abs(result - target)) -> {
//                        result = sum
//                        while (left < right && nums[left] == nums[left + 1]) left++
//                        while (left < right && nums[right] == nums[right - 1]) right--
//                        left++
//                        right--
//                    }
                    (sum < target) -> {
                        while (left < right && nums[left] == nums[left + 1]) left++
                        left++
                    }

                    (sum > target) -> {
                        while (left < right && nums[right] == nums[right - 1]) right--
                        right--
                    }
                    else -> {
                        return 0
                    }

                }
                if (abs(sum - target) < abs(result - target)) {
                    result = sum
                }
            }
        }
        return result
    }
