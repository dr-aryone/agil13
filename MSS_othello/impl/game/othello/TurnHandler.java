package game.othello;

import game.logic.StandardRuleStrategy;
import game.othello.score.Score_impl;

import java.util.List;
import java.util.Random;

import kth.game.othello.Rules;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

/**
 * The responsibility of this class is to handle the turn of the players playing
 * othello
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class TurnHandler {
	private final List<Player> players;
	private int playerInTurn = 0;
	private final Rules rules;
	private final Score score;

	/**
	 * Create a new turnHandler that handles the specified players
	 * 
	 * @param players
	 *            the players taking turns
	 * @param rules
	 *            the rules of the game
	 * @param score
	 */
	public TurnHandler(List<Player> players, StandardRuleStrategy rules, Score score) {
		this.players = players;
		this.rules = rules;
		this.score = score;
	}

	/**
	 * Start the turns with a random player starting
	 */
	public void start() {
		Random random = new Random();
		playerInTurn = random.nextInt(players.size());
	}

	/**
	 * Start the turns with the specified player starting
	 * 
	 * @param playerId
	 *            the id of the player starting the turns
	 */
	public void start(String playerId) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId().equals(playerId)) {
				playerInTurn = i;
				return;
			}
		}
		throw new IllegalArgumentException("Player Id does not exist");
	}

	/**
	 * 
	 * @return the player that is next in turn
	 */
	public Player getPlayerInTurn() {
		for (int i = 0; i < players.size(); i++) {
			String playerId = players.get(playerInTurn).getId();
			if (rules.hasValidMove(playerId)) {
				return players.get(playerInTurn);
			} else {
				/*
				 * Necessary cast since Score doesn't contain any methods for
				 * mutating scores
				 */
				((Score_impl) score).adjustScore(playerId, -2);
			}
			this.changePlayer();
		}

		return null;
	}

	/**
	 * 
	 * @return the players taking turns
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Set the next player as the player who is next in turn
	 */
	public void changePlayer() {
		this.playerInTurn = (this.playerInTurn + 1) % this.players.size();
	}

	/**
	 * Checks if the specified player is next in turn
	 * 
	 * @param playerId
	 *            the id of the player to check
	 * @return true if the player is next in turn, otherwise false
	 */
	public boolean playerInTurn(String playerId) {
		return this.getPlayerInTurn().getId().equals(playerId);
	}
}
