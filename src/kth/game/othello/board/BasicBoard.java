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
	public String toString() {
		char[][] board = new char[10][10];
		for (char[] row : board) {
			Arrays.fill(row, '#');
		}
		for (Node node : nodes) {
			char nodeChar = node.getOccupantPlayerId() == null ? ' ' : (node.getOccupantPlayerId().charAt(0));
			board[node.getXCoordinate() + 1][node.getYCoordinate() + 1] = nodeChar;
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
}
