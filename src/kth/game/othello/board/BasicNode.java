package kth.game.othello.board;

import kth.game.othello.OthelloConstants;
import kth.game.othello.player.Player;

/**
 * An implementation of the {@link Node} interface used in the game of Othello.
 * 
 */
public class BasicNode implements Node, OthelloConstants {

	private static int nextId = 1;

	private final String id, occupantPlayerId;
	private final int xCoordinate, yCoordinate;

	/**
	 * Create a new {@link BasicNode} with the given x- and y-coordinates. The
	 * ID of this node will be set to an auto-incremented value, and the
	 * occupant player ID will be <code>null</code>.
	 * 
	 * @param xCoordinate
	 *            The x-coordinate of this node
	 * @param yCoordinate
	 *            The y-coordinate of this node
	 */
	public BasicNode(int xCoordinate, int yCoordinate) {
		this(xCoordinate, yCoordinate, getNextId(), null);
	}

	BasicNode(int xCoordinate, int yCoordinate, String id, String occupantPlayerId) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.id = id;
		this.occupantPlayerId = occupantPlayerId;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getOccupantPlayerId() {
		return occupantPlayerId;
	}

	@Override
	public int getXCoordinate() {
		return xCoordinate;
	}

	@Override
	public int getYCoordinate() {
		return yCoordinate;
	}

	@Override
	public boolean isMarked() {
		return getOccupantPlayerId() != null;
	}

	/**
	 * Create a new {@link BasicNode}, as a copy of the given node but with the
	 * occupant player set to the given player.
	 * 
	 * @param node
	 *            The node to occupy and copy
	 * @param player
	 *            The player to occupy the returned node
	 * @return A new node with the given player set as occupant player
	 */
	public static Node newNodeOccupiedByPlayer(Node node, Player player) {
		return new BasicNode(node.getXCoordinate(), node.getYCoordinate(), node.getId(), player.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BasicNode))
			return false;
		BasicNode b = (BasicNode) obj;
		return getId().equals(b.getId()) && getOccupantPlayerId().equals(b.getOccupantPlayerId())
				&& getXCoordinate() == b.getXCoordinate() && getYCoordinate() == b.getYCoordinate();
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + getId().hashCode();
		result = result * 31 + getOccupantPlayerId().hashCode();
		result = result * 31 + getXCoordinate();
		result = result * 31 + getYCoordinate();
		return result;
	}

	@Override
	public String toString() {
		return String.format("Node x: %d, y: %d, id: %s, player: %s", getXCoordinate(), getYCoordinate(), getId(),
				getOccupantPlayerId());
	}

	private static String getNextId() {
		int id = nextId++;
		if (id % 10 == OTHELLO_BOARD_SIDE_LENGTH)
			nextId += 2; // keep ID aligned with board dimensions
		return String.valueOf(id);
	}
}
