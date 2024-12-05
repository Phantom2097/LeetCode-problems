/*  Только перед надо */
fun shortestPalindrome(s: String): String {

    if (s.length < 2) return s
    val reverseS = s.reversed()
    //  решётка нужна, чтобы строки не слились в одну, на её индексе в массиве будет ноль
    val newS = "$s#$reverseS"

    // храним в массиве количество совпадений элементов строки
    val arr = IntArray(newS.length) // храним в массиве количество совпадений элементов строки

    var j = 0
    for (i in 1..<newS.length) {
        // Останавливаемся, если j = 0, то есть совпадений, после нулевого элемента не было. Или останавливаемся, если два элемента совпали
        while (j > 0 && newS[i] != newS[j]) { // Останавливаемся, если j = 0, то есть совпадений, после нулевого элемента не было. Или останавливаемся, если два элемента совпали
            j = arr[j - 1]
        }
        // увеличиваем j, если текущий элемент совпал с нулевым
        if (newS[i] == newS[j]) j++
        // записываем количество совпадений, которое равно j в текущий элемент списка
        arr[i] = j
    }

    val toAdd = s.length - arr.last()

    return reverseS.substring(0, toAdd) + s

}

fun main(args: Array<String>) {
    println(shortestPalindrome("aacecaaa"))
}
