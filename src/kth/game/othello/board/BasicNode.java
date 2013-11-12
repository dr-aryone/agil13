package kth.game.othello.board;

public class BasicNode implements Node {

	private final String id, occupantPlayerId;
	private final int xCoordinate, yCoordinate;

	public BasicNode(int xCoordinate, int yCoordinate, String id, String occupantPlayerId) {
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
		return getOccupantPlayerId() == null;
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
}
