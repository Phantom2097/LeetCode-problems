class MyCalendar() : ArrayList<Pair<Int, Int>>() {
    fun book(start: Int, end: Int): Boolean =
        none { (first, second) -> start < second && end > first }
            .also { if (it) add(start to end) }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * var obj = MyCalendar()
 * var param_1 = obj.book(start,end)
 */