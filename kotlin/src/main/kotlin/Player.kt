import NonWinningScore.FORTY
import NonWinningScore.ZERO

data class Player(val name: String, private val internalScore: Score = ZERO) {

    fun scores(): Player = this.copy(internalScore = internalScore.next())

    fun score(): String = internalScore.representation

    fun hasSameScoreAs(other: Player): Boolean = this.internalScore == other.internalScore

    fun hasScoredOver30(): Boolean = this.internalScore is FORTY || hasScoredOver40()

    fun hasAdvantageOver(other: Player): Boolean =
        hasScoredOver40() && this.internalScore pointsDifferenceOver other.internalScore == 1

    fun hasWonOver(other: Player): Boolean =
        hasScoredOver40() && this.internalScore pointsDifferenceOver other.internalScore >= 2

    private fun hasScoredOver40(): Boolean = this.internalScore is FinalScore
}