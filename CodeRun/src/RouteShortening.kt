import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


/*
    По какой-то причине нельзя сократить полностью, возможно, если прочитать задание, то пойму,
    но а так надо проверять, лежат ли на одной линии действия. В конце докинуть joinToString("\n")
*/

/*
    Не прокатило, видимо нужно сделать мэп и проверять соседние элементы, пока не останется
    минимальное количество
*/
fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

//    var x = 0
//    var y = 0
    val directions = arrayOf("TOP", "BOTTOM", "RIGHT", "LEFT")



    val lines = mutableListOf<String>()
    while (true) {
        val line = readlnOrNull()
        if (line.isNullOrBlank()) break
        val (move, num) = line.split(" ")
        when (move) {
            directions[0] -> lines.add("1 $num")
            directions[1] -> lines.add("1 -$num")
            directions[2] -> lines.add("2 $num")
            directions[3] -> lines.add("2 -$num")
        }
    }

    val answer = ArrayList<String>()
    answer.add(lines[0])
    var (prevMove, prevNum) = lines[0].split(" ")

    for (i in 1 ..< lines.size) {
        val (nowMove, nowNum) = lines[i].split(" ")
        if (nowMove == prevMove) {
            answer.removeLast()
            prevNum = (nowNum.toInt() + prevNum.toInt()).toString()
            if (prevNum != "0") {
                answer.add("$nowMove $prevNum")
            } else {
                if (answer.isNotEmpty()) {
                    prevMove = answer.last().split(" ")[0]
                    prevNum = answer.last().split(" ")[1]
                } else {
                    prevMove = "0"
                    prevNum = "0"
                }
            }
        } else {
            answer.add(lines[i])
            prevMove = nowMove
            prevNum = nowNum
        }
    }

    val result = mutableListOf<String>()
    for (a in answer) {
        val (move, num) = a.split(" ")
        when (move) {
            "1" -> {
                result.add(if (num.toInt() > 0) "TOP $num" else "BOTTOM ${-num.toInt()}")
            }
            "2" -> {
                result.add(if (num.toInt() > 0) "RIGHT $num" else "LEFT ${-num.toInt()}")
            }
        }
    }

    writer.write(result.joinToString("\n"))

    reader.close()
    writer.close()
}