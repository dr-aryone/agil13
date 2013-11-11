package kth.game.othello.board;

class BasicNode implements Node {

	private final String id, occupantPlayerId;
	private final int xCoordinate, yCoordinate;

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
		return getOccupantPlayerId() == null;
	}
}
