import { TennisGame } from "./TennisGame";

export class TennisGame1 implements TennisGame {
  private player1 = {
    score: 0,
    name: "",
  };
  private player2 = {
    score: 0,
    name: "",
  };

  private drawScoreMap = {
    0: "Love-All",
    1: "Fifteen-All",
    2: "Thirty-All",
  };

  private playScoreMap = {
    0: "Love",
    1: "Fifteen",
    2: "Thirty",
    3: "Forty",
  };

  constructor(player1Name: string, player2Name: string) {
    this.player1.name = player1Name;
    this.player2.name = player2Name;
  }

  wonPoint(playerName: string): void {
    if (playerName === this.player1.name) this.player1.score += 1;
    else this.player2.score += 1;
  }

  private getScoreForDraw(score: number): string {
    return this.drawScoreMap[score] || "Deuce";
  }

  private getScoreForMidGame(score: number): string {
    return this.playScoreMap[score];
  }

  private getFinalScore(): string {
    const minusResult: number = this.player1.score - this.player2.score;
    if (minusResult === 1) return "Advantage player1";
    else if (minusResult === -1) return "Advantage player2";
    else if (minusResult >= 2) return "Win for player1";
    else return "Win for player2";
  }

  getScore(): string {
    if (this.player1.score === this.player2.score) {
      return this.getScoreForDraw(this.player1.score);
    }
    if (this.player1.score >= 4 || this.player2.score >= 4) {
      return this.getFinalScore();
    }
    return `${this.getScoreForMidGame(
      this.player1.score
    )}-${this.getScoreForMidGame(this.player2.score)}`;
  }
}
