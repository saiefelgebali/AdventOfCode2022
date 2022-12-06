package days

class Day2 : Day(2) {
    override fun getPartOneAnswer(): String {
        return readInputAsLines().sumOf {
            getScoreByRound(RoundStrategyOne(it))
        }.toString()
    }

    override fun getPartTwoAnswer(): String {
        return readInputAsLines().sumOf {
            getScoreByRound(RoundStrategyTwo(it))
        }.toString()
    }

    private fun getScoreByRound(round: Round): Int {
        val resultPoints = when (round.roundResult) {
            RoundResult.Win -> 6
            RoundResult.Draw -> 3
            RoundResult.Loss -> 0
        }

        val myMovePoints = when (round.myMove) {
            Move.Rock -> 1
            Move.Paper -> 2
            Move.Scissors -> 3
        }

        return resultPoints + myMovePoints
    }
}

enum class Move(val index: Int) {
    Rock(0), Paper(1), Scissors(2);

    companion object {
        fun fromInt(index: Int) = Move.values().first { it.index == index }
    }
}

enum class RoundResult {
    Win, Draw, Loss
}

interface Round {
    val opponentMove: Move
    val myMove: Move
    val roundResult: RoundResult
}

class RoundStrategyOne(line: String) : Round {
    override val opponentMove: Move
    override val myMove: Move
    override val roundResult: RoundResult

    init {
        val moves = line.split(" ")

        opponentMove = when (moves[0]) {
            "A" -> Move.Rock
            "B" -> Move.Paper
            "C" -> Move.Scissors
            else -> throw Error("Received invalid input ${moves[0]}")
        }

        myMove = when (moves[1]) {
            "X" -> Move.Rock
            "Y" -> Move.Paper
            "Z" -> Move.Scissors
            else -> throw Error("Received invalid input ${moves[1]}")
        }

        roundResult = when (val diff = myMove.index - opponentMove.index) {
            1, -2 -> {
                RoundResult.Win
            }

            -1, 2 -> {
                RoundResult.Loss
            }

            0 -> {
                RoundResult.Draw
            }

            else -> throw Error("Received a diff of $diff")
        }
    }
}

class RoundStrategyTwo(line: String) : Round {
    override val opponentMove: Move
    override val myMove: Move
    override val roundResult: RoundResult

    init {
        val moves = line.split(" ")

        opponentMove = when (moves[0]) {
            "A" -> Move.Rock
            "B" -> Move.Paper
            "C" -> Move.Scissors
            else -> throw Error("Received invalid input ${moves[0]}")
        }

        roundResult = when (moves[1]) {
            "X" -> RoundResult.Loss
            "Y" -> RoundResult.Draw
            "Z" -> RoundResult.Win
            else -> throw Error("Received invalid input ${moves[0]}")
        }

        myMove = when (roundResult) {
            RoundResult.Win -> Move.fromInt((opponentMove.index + 1) % 3)
            RoundResult.Draw -> opponentMove
            RoundResult.Loss ->
                if (opponentMove.index == 0)
                    Move.fromInt(2)
                else
                    Move.fromInt((opponentMove.index - 1))
        }
    }
}