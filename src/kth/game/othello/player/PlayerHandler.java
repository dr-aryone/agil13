package kth.game.othello.player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PlayerHandler {
	private final Map<String, Player> playerLookupMap = new LinkedHashMap<>();
	private Player playerInTurn;

	public PlayerHandler(Player playerOne, Player playerTwo) {
		playerLookupMap.put(playerOne.getId(), playerOne);
		playerLookupMap.put(playerTwo.getId(), playerTwo);
	}

	/**
	 * @return one random Player out of the available players
	 */
	public Player randomPlayer() {
		Random r = new Random();
		Player[] players = playerLookupMap.values().toArray(new Player[0]);
		return players[r.nextInt(players.length)];
	}

	/**
	 * Get the player with the given id.
	 * 
	 * @param playerId
	 *            the id of the Player
	 * @return the Player with the given id
	 */
	public Player getPlayer(String playerId) {
		return playerLookupMap.get(playerId);
	}

	/**
	 * Get the player in turn
	 * 
	 * @return the player in turn
	 */
	public Player getPlayerInTurn() {
		return playerInTurn;
	}

	/**
	 * Gets all available players
	 * 
	 * @return a list of all available players
	 */
	public List<Player> getAllPlayers() {
		return new ArrayList<>(playerLookupMap.values());
	}

	/**
	 * Gets the Player who has the next turn.
	 * 
	 * @return the Player who has the next turn.
	 */
	private Player getNextPlayer() {
		for (String id : playerLookupMap.keySet()) {
			if (!getPlayerInTurn().getId().equals(id)) {
				return playerLookupMap.get(id);
			}
		}
		return null;
	}

	/**
	 * Sets which Player is supposed to play.
	 * 
	 * @param playerId
	 *            the id of the player which is supposed to play.
	 */
	public void setPlayerInTurn(String playerId) {
		setPlayerInTurn(getPlayer(playerId));
	}

	private void setPlayerInTurn(Player playerInTurn) {
		this.playerInTurn = playerInTurn;
	}

	/**
	 * Change player to the one who is next in turn.
	 */
	public void changePlayer() {
		setPlayerInTurn(getNextPlayer());
	}

}
