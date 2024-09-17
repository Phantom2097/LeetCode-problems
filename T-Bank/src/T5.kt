import java.io.BufferedReader
import java.io.InputStreamReader

fun competition() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val (hh, mm, ss) = reader.readLine().split(":").map { it.toInt() }
    val startTime = hh * 60 * 60 + mm * 60 + ss

    val n = reader.readLine().toInt()

    val list = mutableListOf<String>()

    val map = mutableMapOf<String, IntArray>()
    val penalties = mutableMapOf<String, IntArray>()

    var prev = "$hh:$mm:$ss"
    repeat(n) {
        val request = reader.readLine().split(" ")

        if (!(map.containsKey(request[0]))) {
            map[request[0]] = IntArray(n) { 0 }
            penalties[request[0]]= IntArray(n) { 0 }
        }
        when (request[3]) {
            "ACCESSED" -> {
                map[request[0]]!![request[2][0] - 'A'] = 1
                penalties[request[0]]!![request[2][0] - 'A'] += minuetsAfterStart(startTime, request[1], prev)
            }
            "DENIED", "FORBIDEN" -> {
                if (map[request[0]]!![request[2][0] - 'A'] != 1) {
                    penalties[request[0]]!![request[2][0] - 'A'] += 20
                }
            }
        }
    }

    val teams = mutableMapOf<String, Pair<Int, Int>>()
    for ((key, _) in map) {
        var first = 0
        var second = 0
        map[key]!!.forEach {
            first += it
        }
        penalties[key]!!.forEach {
            second += it
        }
        teams[key] = Pair(first, second)
    }

    // Сортировка по правилам
    val sortedTeams = teams.toList().sortedWith(
        compareByDescending<Pair<String, Pair<Int, Int>>> { it.second.first }
            .thenBy { it.second.second }
            .thenBy { it.first }
    )

// Вывод отсортированных команд
    var place = 0
    var placeCount = 0
    var prevAccept = -1
    var prevFalse = -1
    sortedTeams.forEach {
        placeCount++
        place = if (prevAccept == it.second.first && prevFalse == it.second.second) {
            place
        } else {
            placeCount
        }
        println("$place ${it.first} ${it.second.first} ${it.second.second}")
        prevAccept = it.second.first
        prevFalse = it.second.second

    }



}

fun minuetsAfterStart(start: Int, now: String, prev: String): Int {
    val (nowH, nowM, nowS) = now.split(":").map { it.toInt() }
    val (prevH, prevM, prevS) = prev.split(":").map { it.toInt() }
    val lastRequest = prevH * 3600 + nowM * 60 + nowS
    var nowRequest = nowH * 3600 + nowM * 60 + nowS
    if (nowRequest < lastRequest) {
        nowRequest += 24 * 3600
    }
    return (nowRequest - start) / 60
}

fun main(args: Array<String>) {
    competition()
}