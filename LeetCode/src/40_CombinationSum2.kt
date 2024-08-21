fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
    // Уберём значения превышающие target т.к. они точно не будут добавлены в список
    val newCandidates = candidates.filter { it <= target }.sorted().toIntArray()
    // Итоговый список
    val result = mutableListOf<List<Int>>()

    // рекурсивная функция для нахождения всех исключительных вариантов
    fun rightCandidates(list: MutableList<Int>, start: Int, target: Int) {
        // Условие выхода из рекурсии с добавлением подходящего списка
        if (target == 0) {
            result.add(ArrayList(list))
            return
        }
        // Другое выходное условие из цикла
        if (target < 0) {
            return
        }
        // Цикл для прохождения по оставшимся элементов начиная со стартового
        for (i in start..< newCandidates.size) {
            // Пропуск повторяющихся элементов
            if (i > start && newCandidates[i] == newCandidates[i - 1]) continue
            // Добавление текущего элемента в список
            list.add(newCandidates[i])
            // Вызов рекурсивной функции для следующего элемента
            rightCandidates(list, i + 1, target - newCandidates[i])
            // Удаление послднего элемента после выхода из рекурсивного вызова
            // для проверки других комбинаций
            list.removeAt(list.size - 1)
        }
    }
    // Вызов рекурсивной функции
    rightCandidates(mutableListOf(), 0, target)
    // Возвращаем итоговое значение
    return result
}
