package game.othello;

import game.logic.StandardRuleStrategy;

import java.util.List;
import java.util.Random;

import kth.game.othello.Rules;
import kth.game.othello.player.Player;

/**
 * The responsibility of this class is to handle the turn of the players playing
 * othello
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class TurnHandler {
	private List<Player> players;
	private int playerInTurn = 0;
	private Rules rules;

	/**
	 * Create a new turnHandler that handles the specified players
	 * 
	 * @param players the players taking turns
	 * @param rules the rules of the game
	 */
	public TurnHandler(List<Player> players, StandardRuleStrategy rules) {
		this.players = players;
		this.rules = rules;
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
	 * @param playerId the id of the player starting the turns
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
			if (rules.hasValidMove(players.get(playerInTurn).getId())) {
				return players.get(playerInTurn);
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
	 * @param playerId the id of the player to check
	 * @return true if the player is next in turn, otherwise false
	 */
	public boolean playerInTurn(String playerId) {
		return this.getPlayerInTurn().getId().equals(playerId);
	}
}
