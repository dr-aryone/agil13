package kth.game.othello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import kth.game.othello.player.Player;

class PlayerHandler {
	private final Map<String, Player> playerLookupMap = new LinkedHashMap<>();
	private Player playerInTurn;

	PlayerHandler(Player... players) {
		this(Arrays.asList(players));
	}

	PlayerHandler(List<Player> players) {
		for (Player player : players)
			playerLookupMap.put(player.getId(), player);
	}

	/**
	 * @return one random Player out of the available players
	 */
	Player randomPlayer() {
		Random r = new Random();
		List<Player> players = getAllPlayers();
		return players.get(r.nextInt(players.size()));
	}

	/**
	 * Get the player with the given id.
	 * 
	 * @param playerId
	 *            the id of the Player
	 * @return the Player with the given id
	 */
	Player getPlayer(String playerId) {
		return playerLookupMap.get(playerId);
	}

	/**
	 * Get the player in turn
	 * 
	 * @return the player in turn
	 */
	Player getPlayerInTurn() {
		return playerInTurn;
	}

	/**
	 * Gets all available players
	 * 
	 * @return a list of all available players
	 */
	List<Player> getAllPlayers() {
		return new ArrayList<>(playerLookupMap.values());
	}

	/**
	 * Gets the Player who has the next turn.
	 * 
	 * @return the Player who has the next turn.
	 */
	Player getNextPlayer() {
		List<Player> players = getAllPlayers();
		int playerInTurnIndex = players.indexOf(playerInTurn);
		return players.get((playerInTurnIndex + 1) % players.size());
	}

	/**
	 * Sets which Player is supposed to play.
	 * 
	 * @param playerId
	 *            the id of the player which is supposed to play.
	 */
	void setPlayerInTurn(String playerId) {
		setPlayerInTurn(getPlayer(playerId));
	}

	private void setPlayerInTurn(Player playerInTurn) {
		this.playerInTurn = playerInTurn;
	}

	/**
	 * Change player to the one who is next in turn.
	 */
	void changePlayer() {
		setPlayerInTurn(getNextPlayer());
	}

}
