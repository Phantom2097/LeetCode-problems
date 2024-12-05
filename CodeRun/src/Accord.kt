import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.suspendCoroutine

fun main() {
    val flow = flow {
        repeat(3) {
                index -> emit(index)
        }
    }
    runBlocking {
        flow.collect { value -> println(value) }
        flow.collect { value -> println(value) }
    }
}

suspend fun func() {
    print("1")
    suspendCoroutine<Unit> { continuation ->
        print("2")
    }
    print("3")
}