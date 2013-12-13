package game.othello.score;

import game.othello.player.PlayerColour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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

	private HashMap<String, MutableScoreItem> playerScores = new HashMap<String, MutableScoreItem>();

	/**
	 * 
	 * @param players the players that score will be kept on
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
	public void addObserver(Observer observer) {
		super.addObserver(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.score.Score#getPlayersScore()
	 */
	public List<ScoreItem> getPlayersScore() {
		ArrayList<ScoreItem> scoreItems = new ArrayList<ScoreItem>();
		for (MutableScoreItem mutableScoreItem : this.playerScores.values()) {
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
	public int getPoints(String playerId) {
		return this.playerScores.get(playerId).score;
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

		this.playerScores.get(node.getOccupantPlayerId()).increment();

		ArrayList<String> playerIds = new ArrayList<String>();
		playerIds.add(node.getOccupantPlayerId());

		if (oldOccupantPlayerId != null) {
			playerIds.add(oldOccupantPlayerId);
			this.playerScores.get(oldOccupantPlayerId).decrement();
		}
		super.setChanged();
		super.notifyObservers(playerIds);
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

		public void increment() {
			score++;
		}

		public void decrement() {
			score--;
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
	 * @param playerId the id of the player whose score will be incremented
	 */
	public void incrementScoreByOne(String playerId) {
		this.playerScores.get(playerId).score++;
	}
}
