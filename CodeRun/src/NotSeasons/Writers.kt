package NotSeasons

data class Car(
    var tab: Tab? = null
)

data class Tab(
    var car: Car? = null
)

fun main() {
    val car = Car()
    val tab = Tab()
    car.tab = tab
    tab.car = car
    print(car)
}