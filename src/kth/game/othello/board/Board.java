package kth.game.othello.board;

import java.util.List;

/**
 * The responsibility of the board is to gather the nodes included in the game.
 * 
 * @author tomasekholm
 */
public interface Board {

	/**
	 * Returns the node with the given x- and y-coordinate
	 * 
	 * @param x the x-coordinate of the node
	 * @param y the y-coordinate of the node
	 * @return the node with given x- and y-coordinate
	 * @throws IllegalArgumentException if there is no {@link Node} having the specific x- and y-coordinate
	 */
	public Node getNode(int x, int y);

	/**
	 * Returns an ordered list of rows using the natural order in x- and then y-coordinate of the nodes.
	 * 
	 * @return the nodes of the board
	 */
	public List<Node> getNodes();

}
