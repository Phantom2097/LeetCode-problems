package NotSeasons


import kotlinx.coroutines.delay
import java.io.IOException
import kotlin.concurrent.thread

//fun main() {
//    try {
//        throw IOException("", null)
//    } catch (e: Exception) {
//        print("HI")
//        throw e
//    } catch (e:IOException){
//        print("Go")
//    } catch (e: Throwable) {
//        print("Good")
//    } finally {
//        print("Hello")
//    }
//}

//fun main() {
//    val a: Int = Int.MAX_VALUE
//    val b: Long = 1
//
//    var result: Long
//    try {
//        result = a + b.toInt()
//    } catch(e: Exception) {
//        result = - 1
//    }
//    println(result)
//}

fun main() {
    var a = 0
    val t1 = thread {
        print(a)
        print(a)
    }
    val t2 = thread {
        a = 1
        a = 2
    }
    t1.join()
    t2.join()
}