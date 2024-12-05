package NotSeasons

import java.io.File

fun main() {
    val inputFile = File("input.txt")
    val outputFile = File("output.txt")

    outputFile.bufferedWriter().use { writer ->

        inputFile.forEachLine { line ->
            for (i in line.indices) {
                writer.write(
                    if (i % 5 == 4) "&" else line[i].toString()
                )
            }
            writer.newLine()
        }
    }
}

