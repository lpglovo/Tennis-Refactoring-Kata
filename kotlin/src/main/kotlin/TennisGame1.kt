data class Player(val name: String, val score: Int)

data class TennisGame1(
        private val player1Name: String,
        private val player2Name: String,
        private val playerOneScore: Int = 0,
        private val playerTwoScore: Int = 0
) : TennisGame {

    private val player1 = Player(player1Name, playerOneScore)
    private val player2 = Player(player2Name, playerTwoScore)

    override fun wonPoint(playerName: String): TennisGame =
            if (playerName === player1.name)
                this.copy(playerOneScore = player1.score + 1)
            else
                this.copy(playerTwoScore = player2.score + 1)

    override fun getScore(): String = when {
        isTied() -> sameScore()
        playingAdvantages() -> advantagesScore()
        else -> runningGameScore()
    }

    private fun runningGameScore(): String =
            "${intermediateScore(player1.score)}-${intermediateScore(player2.score)}"

    private fun advantagesScore(): String =
            when (player1.score - player2.score) {
                1 -> "Advantage ${player1.name}"
                -1 -> "Advantage ${player2.name}"
                2, 3, 4 -> "Win for ${player1.name}"
                else -> "Win for ${player2.name}"
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
