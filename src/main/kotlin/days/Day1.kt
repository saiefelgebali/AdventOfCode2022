package days

class Elf() {
    var totalCalories: Int = 0

    fun addLine(line: String) {
        totalCalories += (line.toIntOrNull() ?: 0)
    }
}

class Day1 : Day(1) {

    private fun getElves(): List<Elf> {
        val lines = readInputAsLines()

        val elves = mutableListOf<Elf>(Elf())
        lines.forEach each@{ line ->
            if (line.isEmpty()) {
                elves.add(Elf())
                return@each
            }
            elves.last().addLine(line)
        }

        return elves
    }

    override fun getPartOneAnswer(): String {
        return getElves().maxBy { it.totalCalories }.totalCalories.toString()
    }

    override fun getPartTwoAnswer(): String {
        return getElves().sortedBy { it.totalCalories }.take(3).sumOf { it.totalCalories }.toString()
    }
}