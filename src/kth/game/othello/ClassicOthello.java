package kth.game.othello;

import java.util.ArrayList;
import java.util.Collections;
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
		List<Node> nodesToSwap = new ArrayList<>();
		for (Direction direction : Direction.values())
			nodesToSwap.addAll(getNodesToSwapInOneDirection(playerId, nodeId, direction));
		return nodesToSwap;
	}

	private List<Node> getNodesToSwapInOneDirection(String playerId, String nodeId, Direction direction) {
		List<Node> nodesToSwap = new ArrayList<>();

		Node startNode = nodeLookupMap.get(nodeId);
		Node current = step(startNode, direction);

		while (current != null && current.isMarked()) {
			if (current.getOccupantPlayerId().equals(playerId))
				return nodesToSwap;
			nodesToSwap.add(current);
			current = step(current, direction);
		}

		return Collections.emptyList();

	}

	private Node step(Node node, Direction direction) {
		return findNode(node.getXCoordinate() + direction.getXDirection(),
				node.getYCoordinate() + direction.getYDirection());
	}

	private Node findNode(int x, int y) {
		return findNode(board, x, y);
	}

	static Node findNode(Board board, int x, int y) {
		for (Node node : board.getNodes()) {
			if (node.getXCoordinate() == x && node.getYCoordinate() == y)
				return node;
		}
		return null;
	}

	private enum Direction {
		NORTH_WEST(-1, -1), NORTH(0, -1), NORTH_EAST(1, -1), WEST(-1, 0), EAST(1, 0), SOUTH_WEST(-1, 1), SOUTH(0, 1), SOUTH_EAST(
				1, 1);
		private final int xDirection, yDirection;

		Direction(int xDirection, int yDirection) {
			this.xDirection = xDirection;
			this.yDirection = yDirection;
		}

		public int getYDirection() {
			return yDirection;
		}

		public int getXDirection() {
			return xDirection;
		}

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
