package kth.game.othello.tournament;

import java.util.HashMap;
import java.util.Map;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.ScoreItem;

/**
 * This entity is responsible for keeping score of how much score every player
 * has.
 */
public class Results {

	private final Map<String, Integer> resultsMap = new HashMap<>();

	/**
	 * Registers the results of a finished othello instance.
	 * 
	 * @param othello
	 *            The instance of othello to register the results from.
	 */
	public void registerResults(Othello othello) {
		for (ScoreItem scoreItem : othello.getScore().getPlayersScore()) {
			resultsMap.put(scoreItem.getPlayerId(), scoreItem.getScore());
		}
	}

	/**
	 * Gets the points for a given player.
	 * 
	 * @param player
	 *            The player to find the score of.
	 * @return The score, or null if player has no score.
	 */
	public Integer getPointsForPlayer(Player player) {
		if (player == null)
			return null;
		return resultsMap.get(player.getId());
	}
}
