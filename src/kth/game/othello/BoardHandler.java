package kth.game.othello;

import java.util.HashMap;
import java.util.Map;

import kth.game.othello.board.BasicNode;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

class BoardHandler {

	private final Map<String, Node> nodeLookupMap = new HashMap<>();

	private final Board board;

	BoardHandler(Board board) {
		this.board = board;
		for (Node node : board.getNodes())
			nodeLookupMap.put(node.getId(), node);
	}

	/**
	 * Gets a Node object from a given id.
	 * 
	 * @param nodeId
	 *            the id of the node to find
	 * @return the Node object of which getId().equals(nodeId)
	 */
	Node getNode(String nodeId) {
		return nodeLookupMap.get(nodeId);
	}

	/**
	 * Gets a Node object from given x and y.
	 * 
	 * @param x
	 *            the x-coordinate of the node.
	 * @param y
	 *            the y-coordinate of the node.
	 * @return the Node object of which getXCoordinate() == x &&
	 *         getYCoordinate() == y.
	 */
	Node getNode(int x, int y) {
		return board.getNode(x, y);
	}

	/**
	 * Returns the board of the current state.
	 * 
	 * @return the Board of the current state.
	 */
	Board getBoard() {
		return board;
	}

	/**
	 * Claims a Node for the given Player. The Node can either be either marked
	 * or unmarked.
	 * 
	 * @param node
	 *            the Node to claim.
	 * @param player
	 *            the Player to claim the Node.
	 */
	void occupyNodeByPlayer(Node node, Player player) {
		/*
		 * This is not pretty, but it is necessary coupling as there is no other
		 * way for the package kth.game.othello to reach the setOccupantPlayerId
		 * of BasicNode.
		 * 
		 * Sadly, this makes the BasicOthello implementation dependent of which
		 * Node implementation it uses. But as mentioned, there is not other way
		 * to do this than to have this cast somewhere.
		 */
		if (!(node instanceof BasicNode))
			return;
		BasicNode basicNode = (BasicNode) node;
		basicNode.setOccupantPlayerId(player.getId());
	}
}
