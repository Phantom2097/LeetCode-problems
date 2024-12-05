import PossibleStringCount.Companion.possibleStringCount

class PossibleStringCount {
    companion object {
        fun possibleStringCount(word: String, k: Int): Int {
            val mod = 1_000_000_007
            val len = word.length
            var repeated = 1
            var prev = '0'
            val countList = mutableListOf<Int>()

            for (i in 0..<len) {
                if (word[i] == prev) {
                    repeated++
                } else {
                    if (repeated >= k) {
                        countList.add(repeated - k + 1)
                    }
                    repeated = 1
                    prev = word[i]
                }
            }
            // Добавим последний повтор, если он есть
            if (repeated >= k) {
                countList.add(repeated - k + 1)
            }

            // Рассчитаем произведение возможных вариантов
            var totalCombinations = 1L
            for (c in countList) {
                totalCombinations = (totalCombinations * (c + 1)) % mod
            }

            return totalCombinations.toInt()
        }
    }

    // Примеры использования
}


fun main() {
    println(possibleStringCount("aabbccdd", 7))  // Вывод: 5
    println(possibleStringCount("aabbccdd", 8))  // Вывод: 1
    println(possibleStringCount("aaabbb", 3))    // Вывод: 8
}




