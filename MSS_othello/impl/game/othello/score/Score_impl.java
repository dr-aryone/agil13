package game.othello.score;

import game.othello.player.PlayerColour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreItem;

/**
 * The responible of this class is to handle the score of the players
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class Score_impl extends Observable implements Observer, Score {

	private final Map<String, MutableScoreItem> playerScores = new HashMap<String, MutableScoreItem>();
	private final Board board;

	public Score_impl(Board board) {
		this.board = board;
	}

	/**
	 * 
	 * @param players
	 *            the players that score will be kept on
	 */
	public void addPlayers(List<Player> players) {
		for (Player player : players) {
			playerScores.put(player.getId(), new MutableScoreItem(player.getId(), 0));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.score.Score#addObserver(java.util.Observer)
	 */
	@Override
	public void addObserver(Observer observer) {
		super.addObserver(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.score.Score#getPlayersScore()
	 */
	@Override
	public List<ScoreItem> getPlayersScore() {
		List<ScoreItem> scoreItems = new ArrayList<ScoreItem>();
		for (MutableScoreItem mutableScoreItem : playerScores.values()) {
			scoreItems.add(this.scoreItemToImutableObject(mutableScoreItem));
		}
		Collections.sort(scoreItems);
		return scoreItems;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.score.Score#getPoints(java.lang.String)
	 */
	@Override
	public int getPoints(String playerId) {
		return playerScores.get(playerId).score;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (MutableScoreItem sc : playerScores.values()) {
			sb.append(sc.toString());
		}
		return sb.toString();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!(o instanceof Node)) {
			throw new IllegalArgumentException("This listener only listens to Node-objects");
		}
		Node node = (Node) o;
		String oldOccupantPlayerId = (String) arg;

		List<String> playerIds = new ArrayList<String>();
		playerIds.add(oldOccupantPlayerId);
		playerIds.add(node.getOccupantPlayerId());

		increasePlayerScore(node.getOccupantPlayerId(), node);
		if (oldOccupantPlayerId != null)
			decreasePlayerScore(oldOccupantPlayerId, node);

		super.setChanged();
		super.notifyObservers(playerIds);
	}

	private void increasePlayerScore(String playerId, Node node) {
		adjustScore(playerId, scoreOfNode(node));
	}

	private void decreasePlayerScore(String playerId, Node node) {
		adjustScore(playerId, -scoreOfNode(node));
	}

	private int scoreOfNode(Node node) {
		if (nodeIsOnBoundary(node))
			return 2;
		else
			return 1;
	}

	private boolean nodeIsOnBoundary(Node node) {
		int x = node.getXCoordinate();
		int y = node.getYCoordinate();
		if (board.hasNode(x + 1, y) && board.hasNode(x - 1, y) && board.hasNode(x, y + 1) && board.hasNode(x, y - 1))
			return false;
		return true;
	}

	private ScoreItem scoreItemToImutableObject(MutableScoreItem scoreItem) {
		return new ScoreItem(scoreItem.playerId, scoreItem.score);
	}

	private class MutableScoreItem {
		int score;
		String playerId;

		public MutableScoreItem(String playerId, int score) {
			this.score = score;
			this.playerId = playerId;
		}

		@Override
		public String toString() {
			PlayerColour pc = PlayerColour.createPlayerColour();
			return pc.getPlayerColor(playerId) + ":" + score + " ";
		}
	}

	/**
	 * Increment a score by one
	 * 
	 * @param playerId
	 *            the id of the player whose score will be incremented
	 */
	public void incrementScoreByOne(String playerId) {
		adjustScore(playerId, 1);
	}

	/**
	 * Add <code>adjustment</code> to the score associated with the player ID
	 * 
	 * @param playerId
	 *            the id of the player whose score will be adjusted
	 * @param adjustment
	 *            the point adjustment (negative allowed)
	 */
	public void adjustScore(String playerId, int adjustment) {
		playerScores.get(playerId).score += adjustment;
	}
}
