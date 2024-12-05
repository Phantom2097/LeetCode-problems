import CodeBattle.pair_with_maximum_product
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.coroutineContext

suspend fun main() {
    val arr = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    coroutineScope {
        launch {
            repeat(arr.count()) { square(it); delay(5) }
        }
        launch {
            delay(10)
            arr.forEach { println(it) }
        }
        val result = async {
            delay(30)
            "Задача 3"
        }
        println(result.await())
    }

}

fun square(n: Int) {
    println(n * n)
}