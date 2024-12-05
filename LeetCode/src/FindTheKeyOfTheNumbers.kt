class FindTheKeyOfTheNumbers {
    fun generateKey(num1: Int, num2: Int, num3: Int): Int {
        val n1 = num1.toString().padStart(4, '0')
        val n2 = num2.toString().padStart(4, '0')
        val n3 = num3.toString().padStart(4, '0')
        val key = StringBuilder()
        for (i in 0 .. 3) {
            val temp = minOf(n1[i].code, n2[i].code, n3[i].code)
            key.append(temp)
        }
        return key.toString().toInt()
    }
}