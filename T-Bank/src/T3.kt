import java.io.BufferedReader
import java.io.InputStreamReader

fun passwordVasiliy() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val lastLetters = reader.readLine()
    val acceptableLetters = reader.readLine()
    val n = reader.readLine()!!.toInt()

    val deque = ArrayDeque<Char>()

    val list = ArrayList<String>()

    for (letter in lastLetters) {
        if (acceptableLetters.contains(letter)) {
            deque.addLast(letter)
            if (deque.size > n) {
                deque.removeFirst()
            }
            list.add(deque.joinToString(""))
        } else {
            deque.clear()
        }
    }

    val len  = list.maxOf { it.length }
    list.filter { it.length == len }.containsAll(acceptableLetters.split(""))

    list.sortDescending()

    println(list[0])
}

fun main(args: Array<String>) {
    passwordVasiliy()
}