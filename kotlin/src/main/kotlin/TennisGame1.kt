data class TennisGame1(val player1: Player, val player2: Player) : TennisGame {

    override fun wonPoint(playerName: String): TennisGame =
            if (playerName === player1.name)
                this.copy(player1 = player1.scores())
            else
                this.copy(player2 = player2.scores())

    override fun getScore(): String =
            when {
                player1.hasSameScoreAs(player2) -> sameScore(player1)
                player1.hasAdvantageOver(player2) -> "Advantage ${player1.name}"
                player2.hasAdvantageOver(player1) -> "Advantage ${player2.name}"
                player1.hasWonOver(player2) -> "Win for ${player1.name}"
                player2.hasWonOver(player1) -> "Win for ${player2.name}"
                else -> runningGameScore()
            }

    private fun sameScore(player: Player): String =
            if (player.hasScoredOver30()) {
                "Deuce"
            } else {
                "${player.score()}-All"
            }

    private fun runningGameScore(): String =
            "${player1.score()}-${player2.score()}"

}
