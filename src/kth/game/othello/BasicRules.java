package kth.game.othello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kth.game.othello.board.Node;

class BasicRules implements Rules {

	private final BoardHandler boardHandler;

	BasicRules(BoardHandler boardHandler) {
		this.boardHandler = boardHandler;
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

		Node startNode = boardHandler.getNode(nodeId);
		Node current = step(startNode, direction);
		while (current != null && current.isMarked()) {
			if (current.getOccupantPlayerId().equals(playerId))
				return nodesToSwap;
			nodesToSwap.add(current);
			current = step(current, direction);
		}

		return Collections.emptyList();

	}

	@Override
	public boolean hasValidMove(String playerId) {
		for (Node node : boardHandler.getBoard().getNodes()) {
			if (isMoveValid(playerId, node.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return !boardHandler.getNode(nodeId).isMarked() && !getNodesToSwap(playerId, nodeId).isEmpty();
	}

	private Node step(Node node, Direction direction) {
		try {
			return boardHandler.getNode(node.getXCoordinate() + direction.getXDirection(), node.getYCoordinate()
					+ direction.getYDirection());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
