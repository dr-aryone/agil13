package kth.game.othello.tournament;

import kth.game.othello.Othello;
import kth.game.othello.tournament.resultdisplay.ResultDisplay;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * This entity is responsible for playing a tournament.
 */
public class OthelloTournament {

	private final ResultDisplay resultDisplay;
	private final Schedule schedule;
	private final boolean view;

	OthelloTournament(ResultDisplay resultDisplay) {
		this(resultDisplay, new Schedule());
	}

	OthelloTournament(ResultDisplay resultDisplay, Schedule schedule) {
		this(resultDisplay, schedule, false);
	}

	OthelloTournament(ResultDisplay resultDisplay, Schedule schedule, boolean view) {
		this.resultDisplay = resultDisplay;
		this.schedule = schedule;
		this.view = view;
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
			resultDisplay.displayResults(finishedGame.getPlayers(), results);
		}

	}

	private Othello playGame(MatchUp matchUp) {
		Othello currentGame = matchUp.getOthello();
		if (view) {
			OthelloViewFactory.create(currentGame).start(matchUp.getStartingPlayer().getId());
		} else {
			currentGame.start(matchUp.getStartingPlayer().getId());
			while (currentGame.isActive()) {
				currentGame.move();
			}
		}
		return currentGame;
	}
}
