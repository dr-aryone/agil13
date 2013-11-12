package kth.game.othello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

class ClassicOthello implements Othello {

	private final Map<String, Player> playerLookupMap = new LinkedHashMap<>();
	private final Map<String, Node> nodeLookupMap = new HashMap<>();
	private final Board board;

	private Player currentPlayer;

	public ClassicOthello(Board board, Player playerOne, Player playerTwo) {
		this.board = board;
		playerLookupMap.put(playerOne.getId(), playerOne);
		playerLookupMap.put(playerTwo.getId(), playerTwo);
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayerInTurn() {
		return currentPlayer;
	}

	@Override
	public List<Player> getPlayers() {
		return new ArrayList<>(playerLookupMap.values());
	}

	@Override
	public boolean hasValidMove(String playerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Node> move() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start() {
		start(randomPlayer().getId());
	}

	@Override
	public void start(String playerId) {
		currentPlayer = playerLookupMap.get(playerId);

	}

	private Player randomPlayer() {
		Random r = new Random();
		Player[] players = playerLookupMap.values().toArray(new Player[0]);
		return players[r.nextInt(players.length)];
	}
}
