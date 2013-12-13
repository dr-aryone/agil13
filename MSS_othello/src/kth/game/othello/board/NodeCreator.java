package kth.game.othello.board;

/**
 * The responsibility of this entity is to create Nodes.
 */
public interface NodeCreator {

	/**
	 * Creates a node from given coordinates
	 * 
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 * @return a node with given coordinate
	 */
	public Node createNodeWithCoordinate(int x, int y);

	/**
	 * Creates a node from given coordinates that is occupied by the given player
	 * 
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 * @param occupantPlayerId the id of the player that is occupying this node
	 * @return a node with given coordinate
	 */
	public Node createNodeWithCoordinateAndOccupant(int x, int y, String occupantPlayerId);

}
