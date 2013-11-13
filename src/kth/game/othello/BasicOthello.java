package kth.game.othello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kth.game.othello.board.BasicBoard;
import kth.game.othello.board.BasicNode;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

class BasicOthello implements Othello {

	private final Map<String, Node> nodeLookupMap = new HashMap<>();

	private Board board;
	private final PlayerHandler playerHandler;

	public BasicOthello(Board board, Player playerOne, Player playerTwo) {
		this.setBoard(board);
		playerHandler = new PlayerHandler(playerOne, playerTwo);
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

	void claimNode(int x, int y, Player player) {
		claimNode(findNode(x, y), player);
	}

	private void claimNode(Node node, Player player) {
		setNodeOccupation(occupyNodeByPlayer(node, player));
	}

	private Node occupyNodeByPlayer(Node node, Player player) {
		return new BasicNode(node.getXCoordinate(), node.getYCoordinate(), node.getId(), player.getId());
	}

	private void setNodeOccupation(Node node) {
		List<Node> nodes = getBoard().getNodes();
		nodes.set(node.getXCoordinate() * 8 + node.getYCoordinate(), node);
		nodeLookupMap.put(node.getId(), node);
		setBoard(new BasicBoard(nodes));
	}

	static Node findNode(Board board, int x, int y) {
		for (Node node : board.getNodes()) {
			if (node.getXCoordinate() == x && node.getYCoordinate() == y)
				return node;
		}
		return null;
	}

	@Override
	public Player getPlayerInTurn() {
		if (!isActive())
			return null;
		return playerHandler.getPlayerInTurn();
	}

	@Override
	public List<Player> getPlayers() {
		return playerHandler.getAllPlayers();
	}

	@Override
	public boolean hasValidMove(String playerId) {
		for (Node node : getBoard().getNodes()) {
			if (isMoveValid(playerId, node.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isActive() {
		for (Player player : playerHandler.getAllPlayers())
			if (hasValidMove(player.getId()))
				return true;
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return !nodeLookupMap.get(nodeId).isMarked() && !getNodesToSwap(playerId, nodeId).isEmpty();
	}

	@Override
	public List<Node> move() {
		if (getPlayerInTurn().getType() != Player.Type.COMPUTER) {
			throw new IllegalStateException("Current player is not a computer");
		}
		List<Node> nodesToSwap = findBestMoveForCurrentPlayer();
		for (Node node : nodesToSwap)
			claimNode(node, playerHandler.getPlayer(getPlayerInTurn().getId()));
		playerHandler.changePlayer();
		return nodesToSwap;
	}

	private List<Node> findBestMoveForCurrentPlayer() {
		List<Node> bestMove = Collections.emptyList();
		for (Node node : getBoard().getNodes()) {
			if (node.isMarked() || !isMoveValid(getPlayerInTurn().getId(), node.getId()))
				continue;
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
		List<Node> nodesToSwap = getNodesToSwap(playerId, nodeId);
		nodesToSwap.add(nodeLookupMap.get(nodeId));
		for (Node node : nodesToSwap)
			claimNode(node, playerHandler.getPlayer(playerId));
		playerHandler.changePlayer();
		return nodesToSwap;
	}

	@Override
	public void start() {
		start(playerHandler.randomPlayer().getId());
	}

	@Override
	public void start(String playerId) {
		playerHandler.setPlayerInTurn(playerId);
	}

	private void setBoard(Board board) {
		this.board = board;
	}
}
