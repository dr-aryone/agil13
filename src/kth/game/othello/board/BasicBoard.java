package kth.game.othello.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * An implementation of the {@link Board} interface used in the game of Othello.
 * 
 */
public class BasicBoard implements Board {

	private static final Comparator<Node> NODE_SORTER = new Comparator<Node>() {

		@Override
		public int compare(Node n1, Node n2) {
			if (n1.getXCoordinate() > n2.getXCoordinate()) {
				return 1;
			} else if (n1.getXCoordinate() < n2.getXCoordinate()) {
				return -1;
			}
			if (n1.getYCoordinate() > n2.getYCoordinate()) {
				return 1;
			} else if (n1.getYCoordinate() < n2.getYCoordinate()) {
				return -1;
			}
			return 0;
		}
	};

	private final List<Node> nodes;

	/**
	 * Create a new BasicBoard with the given nodes.
	 * 
	 * @param nodes
	 *            The nodes to be held inside this board
	 */
	public BasicBoard(List<Node> nodes) {
		this.nodes = nodes;
		Collections.sort(nodes, NODE_SORTER);
	}

	@Override
	public List<Node> getNodes() {
		return new ArrayList<>(nodes);
	}

	@Override
	public Node getNode(int x, int y) {
		for (Node node : nodes)
			if (node.getXCoordinate() == x && node.getYCoordinate() == y)
				return node;
		throw new IllegalArgumentException("Node not inside board");
	}

	@Override
	public int getMaxX() {
		return getWidth() - 1;
	}

	@Override
	public int getMaxY() {
		return getHeight() - 1;
	}

	@Override
	public boolean hasNode(int x, int y) {
		try {
			getNode(x, y);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public String toString() {
		char[][] board = new char[getWidth() + 2][getHeight() + 2];
		for (char[] row : board) {
			Arrays.fill(row, '#');
		}
		for (Node node : nodes) {
			char nodeChar = node.getOccupantPlayerId() == null ? ' ' : (node.getOccupantPlayerId().charAt(0));
			board[node.getYCoordinate() + 1][node.getXCoordinate() + 1] = nodeChar;
		}

		StringBuilder boardString = new StringBuilder();
		for (char[] row : board) {
			for (char nodeChar : row) {
				boardString.append(nodeChar);
			}
			boardString.append('\n');
		}
		return boardString.toString();
	}

	private int getHeight() {
		int maxX = 0;
		for (Node node : nodes)
			if (node.getXCoordinate() > maxX)
				maxX = node.getXCoordinate();
		return maxX + 1;
	}

	private int getWidth() {
		int maxY = 0;
		for (Node node : nodes)
			if (node.getYCoordinate() > maxY)
				maxY = node.getYCoordinate();
		return maxY + 1;
	}
}
