package kth.game.othello;

public enum Direction {
	NORTH_WEST(-1, -1), NORTH(0, -1), NORTH_EAST(1, -1), WEST(-1, 0), EAST(1, 0), SOUTH_WEST(-1, 1), SOUTH(0, 1), SOUTH_EAST(
			1, 1);
	private final int xDirection, yDirection;

	Direction(int xDirection, int yDirection) {
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}

	public int getYDirection() {
		return yDirection;
	}

	public int getXDirection() {
		return xDirection;
	}

}
