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

class BoardHandler {

	private static final int MIDDLE_UPPER_LEFT_X = 3, MIDDLE_UPPER_LEFT_Y = MIDDLE_UPPER_LEFT_X;

	private final Map<String, Node> nodeLookupMap = new HashMap<>();

	private Board board;

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
	 * Returns the board of the current state.
	 * 
	 * @return the Board of the current state.
	 */
	Board getBoard() {
		return board;
	}

	/**
	 * Places the four initial middle bricks as: white, black black, white
	 * 
	 * @param white
	 *            The player who will be represented as the "black" player.
	 * @param black
	 *            The player who will be represented as the "white" player.
	 */
	void placeInitialBricks(Player white, Player black) {
		occupyNodeByPlayer(board.getNode(MIDDLE_UPPER_LEFT_X, MIDDLE_UPPER_LEFT_Y), white);
		occupyNodeByPlayer(board.getNode(MIDDLE_UPPER_LEFT_X + 1, MIDDLE_UPPER_LEFT_Y), black);
		occupyNodeByPlayer(board.getNode(MIDDLE_UPPER_LEFT_X, MIDDLE_UPPER_LEFT_Y + 1), black);
		occupyNodeByPlayer(board.getNode(MIDDLE_UPPER_LEFT_X + 1, MIDDLE_UPPER_LEFT_Y + 1), white);
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
		Node occupied = BasicNode.newNodeOccupiedByPlayer(node, player);

		List<Node> nodes = getBoard().getNodes();
		int nodeIndex = nodes.indexOf(node);
		nodes.set(nodeIndex, occupied);
		nodeLookupMap.put(node.getId(), occupied);
		board = new BasicBoard(nodes);
	}

	/**
	 * Gets all nodes to swap if the Player would place a brick on that Node.
	 * 
	 * @param playerId
	 *            the id of the Player that is trying to place a brick.
	 * @param nodeId
	 *            the id of the Node that the Player is trying to place a brick
	 *            on.
	 * @return a List of Nodes that would be swapped.
	 */
	List<Node> getNodesToSwap(String playerId, String nodeId) {
		List<Node> nodesToSwap = new ArrayList<>();
		for (Direction direction : Direction.values())
			nodesToSwap.addAll(getNodesToSwapInOneDirection(playerId, nodeId, direction));
		return nodesToSwap;
	}

	private List<Node> getNodesToSwapInOneDirection(String playerId, String nodeId, Direction direction) {
		List<Node> nodesToSwap = new ArrayList<>();

		Node startNode = getNode(nodeId);
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
		return board.getNode(node.getXCoordinate() + direction.getXDirection(),
				node.getYCoordinate() + direction.getYDirection());
	}
}
