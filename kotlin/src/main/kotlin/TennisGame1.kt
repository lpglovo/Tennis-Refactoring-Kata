data class Player(val name: String, val score: Int = 0)

data class TennisGame1(val player1: Player, val player2: Player) : TennisGame {

    override fun wonPoint(playerName: String): TennisGame =
            if (playerName === player1.name)
                this.copy(player1 = player1.copy(score = player1.score + 1))
            else
                this.copy(player2 = player2.copy(score = player2.score + 1))

    override fun getScore(): String = when {
        isTied() -> sameScore(player1.score)
        playingAdvantages() -> advantagesScore()
        else -> runningGameScore()
    }

    private fun isTied() = player1.score == player2.score

    private fun sameScore(score: Int): String =
            when (score) {
                0 -> "Love-All"
                1 -> "Fifteen-All"
                2 -> "Thirty-All"
                else -> "Deuce"
            }

    private fun playingAdvantages() = player1.score >= 4 || player2.score >= 4

    private fun advantagesScore(): String =
            when (player1.score - player2.score) {
                1 -> "Advantage ${player1.name}"
                -1 -> "Advantage ${player2.name}"
                2, 3, 4 -> "Win for ${player1.name}"
                else -> "Win for ${player2.name}"
            }

    private fun runningGameScore(): String =
            "${intermediateScore(player1.score)}-${intermediateScore(player2.score)}"

    private fun intermediateScore(score: Int): String {
        var res = ""
        when (score) {
            0 -> res = "Love"
            1 -> res = "Fifteen"
            2 -> res = "Thirty"
            3 -> res = "Forty"
        }
        return res
    }
}
