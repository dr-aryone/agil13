package kth.game.othello.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

/**
 * The responsibility of this class is to handle of the scores of the players.
 */
public class BasicScore extends Observable implements Score, Observer {

	private final List<ScoreItem> playerScores = new ArrayList<>();

	public BasicScore(List<Player> players) {
		for (Player player : players) {
			playerScores.add(new ScoreItem(player.getId(), 0));
		}
	}

	void incrementPoints(String playerId) {
		changePoints(playerId, 1);
	}

	void decrementPoints(String playerId) {
		changePoints(playerId, -1);
	}

	private void changePoints(String playerId, int points) {
		ScoreItem oldScoreItem = getScoreItem(playerId);
		if (playerScores.remove(oldScoreItem)) {
			playerScores.add(new ScoreItem(oldScoreItem.getPlayerId(), oldScoreItem.getScore() + points));
			setChanged();
			notifyObservers(Collections.singletonList(playerId));
		}
	}

	/**
	 * Parses a board to set the initial score.
	 * 
	 * @param board
	 *            The board to parse nodes from.
	 */
	public void setInitialScore(Board board) {
		for (Node node : board.getNodes()) {
			if (node.getOccupantPlayerId() != null) {
				incrementPoints(node.getOccupantPlayerId());
			}
		}
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

	@Override
	public void update(Observable o, Object arg) {
		if (!(o instanceof Node))
			return;
		Node node = (Node) o;
		incrementPoints(node.getOccupantPlayerId());
		if (!(arg instanceof String))
			return;
		String previousPlayerId = (String) arg;
		decrementPoints(previousPlayerId);
	}
}
