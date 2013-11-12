package kth.game.othello.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

	final List<Node> nodes;

	public BasicBoard(List<Node> nodes) {
		this.nodes = nodes;
		Collections.sort(nodes, NODE_SORTER);
	}

	@Override
	public List<Node> getNodes() {
		return new ArrayList<>(nodes);
	}

}
