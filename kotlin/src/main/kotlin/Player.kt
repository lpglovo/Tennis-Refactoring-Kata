import NonWinningScore.FORTY
import NonWinningScore.ZERO

data class Player(val name: String, private val internalScore: Score = ZERO) {

    fun scores(): Player = this.copy(internalScore = internalScore.next())

    fun hasSameScoreAs(other: Player): Boolean = this.internalScore == other.internalScore

    fun hasAdvantageOver(other: Player): Boolean {
        return hasScoredOver40() && this.internalScore.pointsDifferenceOver(other.internalScore) == 1
    }

    fun hasWonOver(other: Player): Boolean {
        return hasScoredOver40() && this.internalScore.pointsDifferenceOver(other.internalScore) >= 2
    }

    fun hasScoredOver30(): Boolean {
        return this.internalScore is FORTY || this.internalScore is FinalScore
    }

    fun score(): String = internalScore.representation

    private fun hasScoredOver40(): Boolean = this.internalScore is FinalScore
}