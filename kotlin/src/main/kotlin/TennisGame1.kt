data class TennisGame1(
        private val player1Name: String,
        private val player2Name: String,
        private val playerOneScore: Int = 0,
        private val playerTwoScore: Int = 0
) : TennisGame {

    override fun wonPoint(playerName: String): TennisGame =
            if (playerName === player1Name)
                this.copy(playerOneScore = this.playerOneScore + 1)
            else
                this.copy(playerTwoScore = playerTwoScore + 1)

    override fun getScore(): String = when {
        isTied() -> sameScore()
        playingAdvantages() -> advantagesScore()
        else -> runningGameScore()
    }

    private fun runningGameScore(): String =
            "${intermediateScore(playerOneScore)}-${intermediateScore(playerTwoScore)}"

    private fun advantagesScore(): String {
        val minusResult = playerOneScore - playerTwoScore
        return when {
            minusResult == 1 -> "Advantage $player1Name"
            minusResult == -1 -> "Advantage $player2Name"
            minusResult >= 2 -> "Win for $player1Name"
            else -> "Win for $player2Name"
        }
    }

    private fun isTied() = playerOneScore == playerTwoScore

    private fun playingAdvantages() = playerOneScore >= 4 || playerTwoScore >= 4

    private fun sameScore(): String =
            when (playerOneScore) {
                0 -> "Love-All"
                1 -> "Fifteen-All"
                2 -> "Thirty-All"
                else -> "Deuce"
            }

    private fun intermediateScore(tempScore: Int): String {
        var score1 = ""
        when (tempScore) {
            0 -> score1 = "Love"
            1 -> score1 = "Fifteen"
            2 -> score1 = "Thirty"
            3 -> score1 = "Forty"
        }
        return score1
    }
}
