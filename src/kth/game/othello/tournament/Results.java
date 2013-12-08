package kth.game.othello.tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.ScoreItem;

/**
 * This entity is responsible for keeping score of how much score every player
 * has.
 */
public class Results {

	private static final int WINNING_POINTS = 2;
	private static final int TIED_POINTS = 1;
	private final Map<String, Integer> resultsMap = new HashMap<>();

	/**
	 * Registers the results of a finished othello instance.
	 * 
	 * @param othello
	 *            The instance of othello to register the results from.
	 */
	public Results registerGame(Othello othello) {
		List<String> winners = findWinners(othello);
		if (winners.size() > 1) {
			registerTie(winners);
		} else if (winners.size() == 1) {
			registerWin(winners.get(0));
		}

		return this;
	}

	private List<String> findWinners(Othello othello) {
		List<String> winners = new ArrayList<>();
		int bestScore = 0;
		for (ScoreItem scoreItem : othello.getScore().getPlayersScore()) {
			if (scoreItem.getScore() > bestScore) {
				winners.clear();
				winners.add(scoreItem.getPlayerId());
				bestScore = scoreItem.getScore();
			} else if (scoreItem.getScore() == bestScore) {
				winners.add(scoreItem.getPlayerId());
			}
		}
		return winners;
	}

	private void registerWin(String winnerId) {
		int oldScore = resultsMap.get(winnerId) == null ? 0 : resultsMap.get(winnerId);
		resultsMap.put(winnerId, oldScore + WINNING_POINTS);
	}

	private void registerTie(List<String> winners) {
		for (String tiedWinnerId : winners) {
			int oldScore = resultsMap.get(tiedWinnerId) == null ? 0 : resultsMap.get(tiedWinnerId);
			resultsMap.put(tiedWinnerId, oldScore + TIED_POINTS);
		}
	}

	/**
	 * Gets the points for a given player.
	 * 
	 * @param player
	 *            The player to find the score of.
	 * @return The score of the player.
	 */
	public Integer getPointsForPlayer(Player player) {
		Integer result = 0;
		if (player == null)
			return result;
		result = resultsMap.get(player.getId());
		if (result == null)
			return 0;
		return result;
	}
}
