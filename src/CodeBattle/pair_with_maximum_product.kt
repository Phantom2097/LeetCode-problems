package CodeBattle

import java.util.*

/* Напишите функцию, которая берет целое число и возвращает текстовую последовательность на английском языке,
начинающуюся со словесного представления исходного числа, далее следует слово 'is' и словесное представление количества
букв получившегося первого слова, за которым следует запятая. Продолжите последовательность используя предыдущее
получившееся слово как первое, добавьте 'is' и словесное представление количества букв получившегося слова.
Продолжайте пока представление количества символов не станет равным 'four. Так как 'four' состоит из четырех символов,
завершите последовательность добавив слова 'four is magic' и точку. Все целые числа в конечном итоге сойдутся к четырём.
Гарантируется, что переданное число не больше 20.
*/
fun pair_with_maximum_product(num: Int): String {
    val map = mutableMapOf<Int, String>(
        0 to "zero",
        1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
        6 to "six",
        7 to "seven",
        8 to "eight",
        9 to "nine",
        10 to "ten",
        11 to "eleven",
        12 to "twelve",
        13 to "thirteen",
        14 to "fourteen",
        15 to "fifteen",
        16 to "sixteen",
        17 to "seventeen",
        18 to "eighteen",
        19 to "nineteen",
        20 to "twenty"
    )
    var magicWord = map[num]
    var magicLength = 0

    val answer = StringBuilder()
    do {
        magicLength = magicWord!!.length
        answer.append("$magicWord is ${map[magicLength]}, ")
        magicWord = map[magicLength]
    } while (magicLength != 4)
    answer.append("four is magic.")

    return answer.toString()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}