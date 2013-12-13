package game.othello.board;

import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCreator;

/**
 * The responsibility of this class is to create nodes
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class NodeCreatorImpl implements NodeCreator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.board.NodeCreator#createNodeWithCoordinate(int,
	 * int)
	 */
	public Node createNodeWithCoordinate(int x, int y) {
		return new StandardNode(x, y, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * kth.game.othello.board.NodeCreator#createNodeWithCoordinateAndOccupant
	 * (int, int, java.lang.String)
	 */
	public Node createNodeWithCoordinateAndOccupant(int x, int y, String occupantPlayerId) {
		return new StandardNode(x, y, occupantPlayerId);
	}

}
