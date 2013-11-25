package kth.game.othello.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import kth.game.othello.player.Player;

/**
 * The responsibility of this class is to handle of the scores of the players.
 */
public class BasicScore extends Observable implements Score {

	private final List<ScoreItem> playerScores = new ArrayList<>();

	BasicScore(List<Player> players) {
		for (Player player : players) {
			playerScores.add(new ScoreItem(player.getId(), 0));
		}
	}

	/**
	 * Increases the points of a player by the number of points specified by the
	 * parameter.
	 * 
	 * @param playerId
	 *            The id of the player to increase score of.
	 * @param points
	 *            The number of points that the players score increased by.
	 */
	public void increasePoints(String playerId, int points) {
		ScoreItem oldScoreItem = getScoreItem(playerId);
		if (playerScores.remove(oldScoreItem)) {
			playerScores.add(new ScoreItem(oldScoreItem.getPlayerId(), oldScoreItem.getScore() + points));
			setChanged();
			notifyObservers(getPoints(playerId));
		}
	}

	/**
	 * Decreases the points of a player by the number of points specified by the
	 * parameter.
	 * 
	 * @param playerId
	 *            The id of the player to decrease score of.
	 * @param points
	 *            The number of points that the players score decreased by.
	 */
	public void decreasePoints(String playerId, int points) {
		increasePoints(playerId, -points);
	}

	@Override
	public List<ScoreItem> getPlayersScore() {
		return playerScores;
	}

	@Override
	public int getPoints(String playerId) {
		ScoreItem scoreItem = getScoreItem(playerId);
		if (scoreItem != null)
			return scoreItem.getScore();
		else
			return 0;
	}

	private ScoreItem getScoreItem(String playerId) {
		for (ScoreItem scoreItem : playerScores) {
			if (scoreItem.getPlayerId().equals(playerId)) {
				return scoreItem;
			}
		}
		return null;
	}
}
