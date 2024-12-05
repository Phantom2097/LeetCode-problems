package algo6_0.dz2

fun main() {
    val n = readln().toInt()

    val set = BooleanArray(n) { false }

    val interest = readln()
        .split(" ")
        .map() { it.toInt() }

    val utility = readln()
        .split(" ")
        .map { it.toInt() }

    val sortInterest = interest
        .withIndex()
        .sortedWith( compareBy(
            { -it.value },
            { -utility[it.index] },
            { it.index }
        ))

    val sortUtility = utility
        .withIndex()
        .sortedWith( compareBy(
            { -it.value },
            { -interest[it.index] },
            { it.index }
        ))

    var u = 0
    var i = 0

    val mood = readln().split(" ").map { it.toInt() }

    val arr = IntArray(n)

    mood.forEachIndexed{ m, todayMood ->
        if (todayMood == 1) {
            while (set[sortUtility[u].index]) u++
            arr[m] = sortUtility[u].index + 1
            set[sortUtility[u].index] = true
        } else {
            while (set[sortInterest[i].index]) i++
            arr[m] = sortInterest[i].index + 1
            set[sortInterest[i].index] = true
        }
    }
    println(arr.joinToString(" "))
}