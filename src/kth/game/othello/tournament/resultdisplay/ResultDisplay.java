package kth.game.othello.tournament.resultdisplay;

import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.tournament.Results;

/**
 * The implementation of this interface is responsible for printing the results
 * of a tournament.
 */
public interface ResultDisplay {

	/**
	 * Prints the result to the user.
	 * 
	 * @param players
	 *            The players to display the result from.
	 * @param results
	 *            The results of the tournament.
	 */
	void displayResults(List<Player> players, Results results);
}
