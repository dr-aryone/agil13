package kth.game.othello.tournament;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.tournament.resultdisplay.ResultDisplay;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * This entity is responsible for playing a tournament.
 */
public class OthelloTournament {

	private static final int MS_BETWEEN_MOVES = 10;
	private static final int MS_BETWEEN_SWAPS = 10;
	private final List<Player> players;
	private final ResultDisplay resultDisplay;
	private final Schedule schedule;
	private boolean view = false;

	OthelloTournament(List<Player> players, ResultDisplay resultDisplay) {
		this(players, resultDisplay, new Schedule());
	}

	OthelloTournament(List<Player> players, ResultDisplay resultDisplay, Schedule schedule) {
		this.players = players;
		this.resultDisplay = resultDisplay;
		this.schedule = schedule;
	}

	/**
	 * Will play all the games in the tournament, and display the results of
	 * each match.
	 */
	public void playTournament() {
		Results results = new Results();
		while (schedule.hasMoreMatchUps()) {
			MatchUp matchUp = schedule.nextMatchUp();
			Othello finishedGame = playGame(matchUp);
			results.registerGame(finishedGame);
		}
		resultDisplay.displayResults(players, results);
	}

	private Othello playGame(MatchUp matchUp) {
		Othello currentGame = matchUp.getOthello();
		if (view) {
			OthelloViewFactory.create(currentGame, MS_BETWEEN_SWAPS, MS_BETWEEN_MOVES).start(
					matchUp.getStartingPlayer().getId());
		} else {
			currentGame.start(matchUp.getStartingPlayer().getId());
			while (currentGame.isActive()) {
				currentGame.move();
			}
		}
		return currentGame;
	}

	public OthelloTournament displayGraphical() {
		view = true;
		return this;
	}
}
