package kth.game.othello.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kth.game.othello.player.Player;

public class BoardHandler {

	private static final int OTHELLO_BOARD_SIDE_LENGTH = 8;
	private static final int MIDDLE_UPPER_LEFT_X = 3;
	private static final int MIDDLE_UPPER_LEFT_Y = MIDDLE_UPPER_LEFT_X;

	private final Map<String, Node> nodeLookupMap = new HashMap<>();

	private Board board;

	public BoardHandler(Board board) {
		this.board = board;
		for (Node node : board.getNodes())
			nodeLookupMap.put(node.getId(), node);
	}

	public Node getNodeForId(String nodeId) {
		return nodeLookupMap.get(nodeId);
	}

	public Board getBoard() {
		return board;
	}

	public void placeInitialBricks(Player white, Player black) {
		occupyNodeByPlayer(findNode(MIDDLE_UPPER_LEFT_X, MIDDLE_UPPER_LEFT_Y), white);
		occupyNodeByPlayer(findNode(MIDDLE_UPPER_LEFT_X + 1, MIDDLE_UPPER_LEFT_Y), black);
		occupyNodeByPlayer(findNode(MIDDLE_UPPER_LEFT_X, MIDDLE_UPPER_LEFT_Y + 1), black);
		occupyNodeByPlayer(findNode(MIDDLE_UPPER_LEFT_X + 1, MIDDLE_UPPER_LEFT_Y + 1), white);
	}

	public Node findNode(int x, int y) {
		int nodeIndex = x * OTHELLO_BOARD_SIDE_LENGTH + y;
		if (nodeIndex >= 0 && nodeIndex < board.getNodes().size())
			return board.getNodes().get(nodeIndex);
		return null;
	}

	public void occupyNodeByPlayer(Node node, Player player) {
		int x = node.getXCoordinate();
		int y = node.getYCoordinate();
		Node occupied = new BasicNode(node.getXCoordinate(), node.getYCoordinate(), node.getId(), player.getId());

		List<Node> nodes = getBoard().getNodes();
		nodes.set(x * OTHELLO_BOARD_SIDE_LENGTH + y, occupied);
		nodeLookupMap.put(node.getId(), occupied);
		setBoard(new BasicBoard(nodes));
	}

	private void setBoard(BasicBoard board) {
		this.board = board;
	}
}
