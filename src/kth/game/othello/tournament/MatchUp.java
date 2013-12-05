package kth.game.othello.tournament;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;

class MatchUp {

	private final Player startingPlayer;
	private final Othello othello;

	MatchUp(Player startingPlayer, Othello othello) {
		this.startingPlayer = startingPlayer;
		this.othello = othello;
	}

	Player getStartingPlayer() {
		return startingPlayer;
	}

	Othello getOthello() {
		return othello;
	}
}
