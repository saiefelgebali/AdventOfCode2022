package days

class Rucksack(line: String) {
    private val firstCompartment: CharSequence
    private val secondCompartment: CharSequence

    init {
        val midPoint = line.length / 2
        firstCompartment = line.subSequence(0, midPoint)
        secondCompartment = line.subSequence(midPoint, line.length)
    }

    fun findCommonItem(): Char {
        return firstCompartment.first {
            item -> secondCompartment.contains(item)
        }
    }
}

class Day3 : Day(3) {
    private val lines = readInputAsLines()
    private val charPositions = listOf('a'..'z', 'A'..'Z').flatten()

    override fun getPartOneAnswer(): String {
        return lines.sumOf {
            charPositions.indexOf(Rucksack(it).findCommonItem()) + 1
        }.toString()
    }

    override fun getPartTwoAnswer(): String {
        return ""
    }
}