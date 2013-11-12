package kth.game.othello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import kth.game.othello.player.Player;

class PlayerHandler {
	private final Map<String, Player> playerLookupMap = new HashMap<>();
	private Player playerInTurn;

	PlayerHandler(Player playerOne, Player playerTwo) {
		playerLookupMap.put(playerOne.getId(), playerOne);
		playerLookupMap.put(playerTwo.getId(), playerTwo);
	}

	Player randomPlayer() {
		Random r = new Random();
		Player[] players = playerLookupMap.values().toArray(new Player[0]);
		return players[r.nextInt(players.length)];
	}

	Player getPlayer(String playerId) {
		return playerLookupMap.get(playerId);
	}

	Player getPlayerInTurn() {
		return playerInTurn;
	}

	List<Player> getAllPlayers() {
		return new ArrayList<>(playerLookupMap.values());
	}

	private Player getNextPlayer() {
		for (String id : playerLookupMap.keySet()) {
			if (!getPlayerInTurn().getId().equals(id)) {
				return playerLookupMap.get(getPlayerInTurn());
			}
		}
		return null;
	}

	void setPlayerInTurn(String playerId) {
		setPlayerInTurn(getPlayer(playerId));
	}

	private void setPlayerInTurn(Player playerInTurn) {
		this.playerInTurn = playerInTurn;
	}

	void changePlayer() {
		setPlayerInTurn(getNextPlayer());
	}

}
