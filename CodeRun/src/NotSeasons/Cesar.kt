package NotSeasons

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        try {
            val scope = CoroutineScope(
                CoroutineName("Root") +
                        SupervisorJob() +
                        CoroutineExceptionHandler { _, _ ->
                            println("Ave, Caesar....")
                        }
            )
            val handler = CoroutineExceptionHandler { _, _ ->
                println("Aurea medi....")
            }
            scope.async(handler) {
                async {
                    Gson().fromJson("Repetitio est ....")
                }
            }.await()
            delay(1000)
            println("Fiat ...")
        } catch (e: Exception) {
            println("Fiest ...")
        }
    }
}

class Gson() {
    fun fromJson(json: String): String { return "g"}
}