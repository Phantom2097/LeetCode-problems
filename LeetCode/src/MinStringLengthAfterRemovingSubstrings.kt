//public class MinStringLengthAfterRemovingSubstrings {
    fun minLength(s: String): Int {
        var str = StringBuilder(s)
        val regex = Regex("AB|CD")
        while (str.contains(regex)) {
            str = StringBuilder(str.replace(regex, ""))
        }
        return str.length
    }

//}
fun main() {
    println(minLength("ABFCACDB"))
}
