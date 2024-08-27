import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private fun transform(s1: String, s2: String): Boolean {
    val map1 = mutableMapOf<Char, Char>()
    val map2 = mutableMapOf<Char, Char>()

    for (i in s1.indices) {
        val c1 = s1[i]
        val c2 = s2[i]

        if (map1.containsKey(c1) && map1[c1] != c2) {
            return false
        }
        if (map2.containsKey(c2) && map2[c2] != c1) {
            return false
        }

        map1[c1] = c2
        map2[c2] = c1
    }

    return true
}

/*
	Попробовать сделать массив букв, которые используются или можно сделать пары с такими штуками
    и проверять входит уже какая-то буква или нет
*/
fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val n = reader.readLine().toInt()

    val result = mutableListOf<String>()

    repeat(n) {
        val s1 = reader.readLine()
        val s2 = reader.readLine()

        if (s1.length != s2.length) {
            result.add("NO")
        } else if (transform(s1, s2)) {
            result.add("YES")
        } else {
            result.add("NO")
        }
    }

    writer.write(result.joinToString("\n"))


//    for (i in 1 .. n) {
//        val substitutions = mutableMapOf<Char, Char>()
//
//
//        if (s1.length != s2.length) {
//            writer.write("NO\n")
//        } else {
//            for (i in s1.indices) {
//                if (s1[i] != s2[i]) {
//                    if (substitutions[s1[i]] != null || substitutions[s2[i]] != null) {
//                        if (substitutions[s1[i]] != s2[i] || substitutions[s2[i]] !=  {}
//                    }
//                } else {
//                    continue
//                }
//            }
//        }
//    }
    reader.close()
    writer.close()
}