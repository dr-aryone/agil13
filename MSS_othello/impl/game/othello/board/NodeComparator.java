package game.othello.board;

import java.util.Comparator;

import kth.game.othello.board.Node;

/**
 * The responsibility of this class is to compare nodes
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node arg0, Node arg1) {
		if (arg0.getXCoordinate() < arg1.getXCoordinate()) {
			return -1;
		} else if (arg0.getXCoordinate() > arg1.getXCoordinate()) {
			return 1;
		} else if (arg0.getYCoordinate() < arg1.getYCoordinate()) {
			return -1;
		} else if (arg0.getYCoordinate() > arg1.getYCoordinate()) {
			return 1;
		}
		return 0;
	}

}
