package NotSeasons

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M) = reader.readLine().split(" ").map { it.toInt() }

    val peopleById = mutableMapOf<String, String>()
    val peopleByName = mutableMapOf<String, Int>()

    repeat(N) {
        val (name, id) = reader.readLine().split(" ")
        val lowerCaseName = name.lowercase()

        peopleById[id.lowercase()] = name
        peopleByName[lowerCaseName] = peopleByName.getOrDefault(lowerCaseName, 0) + 1
    }

    var idealMatches = 0
    var satisfactoryMatches = 0

    repeat(M) {
        val (letterName, letterId) = reader.readLine().split(" ")
        val lowerCaseLetterName = letterName.lowercase()
        val lowerCaseLetterId = letterId.lowercase()

        if (lowerCaseLetterId in peopleById) {
            val actualName = peopleById[lowerCaseLetterId]!!

            if (letterName.equals(actualName, ignoreCase = true)) {
                idealMatches++
            } else {
                satisfactoryMatches++
            }
        } else if (peopleByName.getOrDefault(lowerCaseLetterName, 0) == 1) {
            satisfactoryMatches++
        }
    }

    writer.write("$idealMatches $satisfactoryMatches")

    reader.close()
    writer.close()
}
