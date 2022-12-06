package days

import java.io.File

abstract class Day(private val dayNumber: Int) {
    abstract fun getPartOneAnswer(): String

    abstract fun getPartTwoAnswer(): String

    fun readInputAsLines(): Array<String> {
        val inputFile = this::class.java.getResource("/input-$dayNumber.txt").let {
            if (it == null) {
                throw Error("Invalid input file")
            }
            it
        }

        return File(inputFile.path).readLines().toTypedArray()
    }

    fun printDay() {
        println("Day $dayNumber")
        println("Part one: ${getPartOneAnswer()}")
        println("Part two: ${getPartTwoAnswer()}")
    }
}

class DayList(private vararg val days: Day) {

    fun printEachDay() {
        days.forEach { it.printDay() }
    }

    fun printLastDay() {
        days.last().printDay()
    }

}