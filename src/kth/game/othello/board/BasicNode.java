package kth.game.othello.board;

import java.util.Observable;

/**
 * An implementation of the {@link Node} interface used in the game of Othello.
 * 
 */
public class BasicNode extends Observable implements Node {

	private static int nextId = 1;

	private final String id;
	private final int xCoordinate, yCoordinate;
	private String occupantPlayerId;

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
	BasicNode(int xCoordinate, int yCoordinate) {
		this(xCoordinate, yCoordinate, getNextId(), null);
	}

	BasicNode(int xCoordinate, int yCoordinate, String occupantPlayerId) {
		this(xCoordinate, yCoordinate, getNextId(), occupantPlayerId);
	}

	private BasicNode(int xCoordinate, int yCoordinate, String id, String occupantPlayerId) {
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

	public void setOccupantPlayerId(String occupantPlayerId) {
		String previousPlayerId = this.occupantPlayerId;
		this.occupantPlayerId = occupantPlayerId;
		setChanged();
		notifyObservers(previousPlayerId);
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
		result = result * 31 + (getOccupantPlayerId() != null ? getOccupantPlayerId().hashCode() : 0);
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
		return String.valueOf(nextId++);
	}
}
