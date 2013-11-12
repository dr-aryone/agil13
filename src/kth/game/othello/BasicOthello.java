package kth.game.othello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import kth.game.othello.board.BasicBoard;
import kth.game.othello.board.BasicNode;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

class BasicOthello implements Othello {

	private final Map<String, Player> playerLookupMap = new LinkedHashMap<>();
	private final Map<String, Node> nodeLookupMap = new HashMap<>();

	private Board board;
	private Player playerInTurn;

	public BasicOthello(Board board, Player playerOne, Player playerTwo) {
		this.setBoard(board);
		playerLookupMap.put(playerOne.getId(), playerOne);
		playerLookupMap.put(playerTwo.getId(), playerTwo);
		for (Node node : board.getNodes())
			nodeLookupMap.put(node.getId(), node);
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

		for (Node current = step(startNode, direction); current != null && current.isMarked(); current = step(current,
				direction)) {
			if (current.getOccupantPlayerId().equals(playerId))
				return nodesToSwap;
			nodesToSwap.add(current);
		}

		return Collections.emptyList();

	}

	private Node step(Node node, Direction direction) {
		return findNode(node.getXCoordinate() + direction.getXDirection(),
				node.getYCoordinate() + direction.getYDirection());
	}

	private Node findNode(int x, int y) {
		return findNode(getBoard(), x, y);
	}

	void occupyNodeByPlayer(int x, int y, Player player) {
		occupyNodeByPlayer(findNode(x, y), player);
	}

	private void occupyNodeByPlayer(Node node, Player player) {
		Node occupiedNode = new BasicNode(node.getXCoordinate(), node.getYCoordinate(), node.getId(), player.getId());
		List<Node> nodes = getBoard().getNodes();
		nodes.set(node.getXCoordinate() * 8 + node.getYCoordinate(), occupiedNode);
		nodeLookupMap.put(occupiedNode.getId(), occupiedNode);
		setBoard(new BasicBoard(nodes));
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
		return playerInTurn;
	}

	@Override
	public List<Player> getPlayers() {
		return new ArrayList<>(playerLookupMap.values());
	}

	@Override
	public boolean hasValidMove(String playerId) {
		for (Node node : getBoard().getNodes()) {
			if (!node.isMarked() && isMoveValid(playerId, node.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isActive() {
		for (Node node : getBoard().getNodes()) {
			if (!node.isMarked()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return !getNodesToSwap(playerId, nodeId).isEmpty();
	}

	@Override
	public List<Node> move() {
		if (getPlayerInTurn().getType() != Player.Type.COMPUTER) {
			throw new IllegalStateException("Current player is not a computer");
		}
		List<Node> bestMoveForCurrentPlayer = findBestMoveForCurrentPlayer();
		setPlayerInTurn(getNextPlayer());
		return bestMoveForCurrentPlayer;
	}

	private Player getNextPlayer() {
		for (String id : playerLookupMap.keySet()) {
			if (!getPlayerInTurn().getId().equals(id)) {
				return playerLookupMap.get(getPlayerInTurn());
			}
		}
		return null;
	}

	private List<Node> findBestMoveForCurrentPlayer() {
		List<Node> bestMove = Collections.emptyList();
		for (Node node : getBoard().getNodes()) {
			List<Node> currentMove = getNodesToSwap(getPlayerInTurn().getId(), node.getId());
			currentMove.add(nodeLookupMap.get(node.getId()));
			if (currentMove.size() > bestMove.size()) {
				bestMove = currentMove;
			}
		}
		return bestMove;
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		if (!isMoveValid(playerId, nodeId)) {
			throw new IllegalArgumentException("Move is not valid");
		}
		List<Node> moves = getNodesToSwap(playerId, nodeId);
		moves.add(nodeLookupMap.get(nodeId));
		setPlayerInTurn(getNextPlayer());
		return moves;
	}

	@Override
	public void start() {
		start(randomPlayer().getId());
	}

	@Override
	public void start(String playerId) {
		setPlayerInTurn(playerLookupMap.get(playerId));

	}

	private Player randomPlayer() {
		Random r = new Random();
		Player[] players = playerLookupMap.values().toArray(new Player[0]);
		return players[r.nextInt(players.length)];
	}

	private void setBoard(Board board) {
		this.board = board;
	}

	private void setPlayerInTurn(Player playerInTurn) {
		this.playerInTurn = playerInTurn;
	}
}
