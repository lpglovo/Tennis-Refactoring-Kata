sealed class Score(open val value: Int = 0, val representation: String) {
    abstract fun next(): Score

    fun pointsDifferenceOver(other: Score): Int = this.value - other.value
}

sealed class NonWinningScore(value: Int, representation: String) : Score(value, representation) {

    object ZERO : NonWinningScore(0, "Love") {
        override fun next(): Score = FIFTEEN
    }

    object FIFTEEN : NonWinningScore(1, "Fifteen") {
        override fun next(): Score = THIRTY
    }

    object THIRTY : NonWinningScore(2, "Thirty") {
        override fun next(): Score = FORTY
    }

    object FORTY : NonWinningScore(3, "Forty") {
        override fun next(): Score = FinalScore(4)
    }
}

data class FinalScore(override val value: Int): Score(value, "") {
    override fun next(): Score = FinalScore(this.value + 1)
}