package kth.game.othello.board;

public class BasicNode implements Node, Comparable<Node> {

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
	public int compareTo(Node arg) {
		if (getXCoordinate() > arg.getXCoordinate()) {
			return 1;
		} else if (getXCoordinate() < arg.getXCoordinate()) {
			return -1;
		}
		if (getYCoordinate() > arg.getYCoordinate()) {
			return 1;
		} else if (getYCoordinate() < arg.getYCoordinate()) {
			return -1;
		}

		return 0;
	}
}
