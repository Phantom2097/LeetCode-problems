
fun main(args: Array<String>) {
    println(diffWaysToCompute("2*3-4*5").joinToString(" "))
}
fun diffWaysToCompute(expression: String): List<Int> {
    val regular = Regex("[+*\\-]")
    val list = expression.split(regular).map { it.toInt() }.toMutableList()
    val symbols = regular.findAll(expression).map { it.value[0] }.toMutableList()

    return helper(symbols, list)

}
fun helper(symbols: MutableList<Char>, digits: MutableList<Int>): List<Int> {
    if (digits.size == 1) return digits

    val result = mutableListOf<Int>()

    for (i in symbols.indices) {
        val leftDigits = digits.subList(0, i + 1).toMutableList()
        val rightDigits = digits.subList(i + 1, digits.size).toMutableList()

        val leftSymbols = symbols.subList(0, i).toMutableList()
        val rightSymbols = symbols.subList(i + 1, symbols.size).toMutableList()

        val leftResults = helper(leftSymbols, leftDigits)
        val rightResults = helper(rightSymbols, rightDigits)

        for (left in leftResults) {
            for (right in rightResults) {
                result.add(when (symbols[i]) {
                    '+' -> {
                        left + right
                    }
                    '-' -> {
                        left - right
                    }
                    '*' -> {
                        left * right
                    }
                    else -> 0
                })
            }
        }

    }
     return result
}



