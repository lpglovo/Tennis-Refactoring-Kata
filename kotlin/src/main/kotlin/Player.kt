data class Player(val name: String, private val score: Int = 0) {
    fun scores(): Player = this.copy(score = score + 1)
    fun hasSameScoreAs(other: Player): Boolean = this.score == other.score
    fun hasAdvantageOver(other: Player): Boolean {
        return hasScoredOver40() && (this.score - other.score) == 1
    }

    fun hasWonOver(other: Player): Boolean {
        return hasScoredOver40() && (this.score - other.score) >= 2
    }

    fun hasScoredOver30(): Boolean {
        return this.score > 2
    }

    fun score(): String =
            when (score) {
                0 -> "Love"
                1 -> "Fifteen"
                2 -> "Thirty"
                3 -> "Forty"
                else -> ""
            }

    private fun hasScoredOver40(): Boolean = this.score >= 4
}