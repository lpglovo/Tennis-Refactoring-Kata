
public class TennisGame1 implements TennisGame {

    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            scorePlayer1 += 1;
        else
            scorePlayer2 += 1;
    }

    public String getScore() {
        if (playersAreEven()) {
            return scoreWhenPlayersAreEven();
        } else if (thereCouldBeAWinner()) {
            return scoreWhenThereCouldBeAWinner();
        } else {
            return scoreWhenGameIsRunning();
        }
    }

    private boolean playersAreEven() {
        return scorePlayer1 == scorePlayer2;
    }

    private boolean thereCouldBeAWinner() {
        return scorePlayer1 >= 4 || scorePlayer2 >= 4;
    }

    private String scoreWhenThereCouldBeAWinner() {
        String score;
        int minusResult = scorePlayer1 - scorePlayer2;
        if (minusResult == 1) score = "Advantage " + player1Name;
        else if (minusResult == -1) score = "Advantage " + player2Name;
        else if (minusResult >= 2) score = "Win for " + player1Name;
        else score = "Win for player2";
        return score;
    }

    private String scoreWhenGameIsRunning() {
        return new Score(scorePlayer1).asString() + "-" + new Score(scorePlayer2).asString();
    }

    private String scoreWhenPlayersAreEven() {
        String score;
        switch (scorePlayer1) {
            case 0:
                score = "Love-All";
                break;
            case 1:
                score = "Fifteen-All";
                break;
            case 2:
                score = "Thirty-All";
                break;
            default:
                score = "Deuce";
                break;

        }
        return score;
    }

    private static class Score {
        private final int score;

        public Score(int score) {
            this.score = score;
        }

        public String asString() {
            String s = "";
            switch (score) {
                case 0:
                    s = "Love";
                    break;
                case 1:
                    s = "Fifteen";
                    break;
                case 2:
                    s = "Thirty";
                    break;
                case 3:
                    s = "Forty";
                    break;
            }
            return s;
        }
    }
}
