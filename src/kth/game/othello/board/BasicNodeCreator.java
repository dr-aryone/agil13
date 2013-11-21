package kth.game.othello.board;

public class BasicNodeCreator implements NodeCreator {

	@Override
	public Node createNodeWithCoordinate(int x, int y) {
		return new BasicNode(x, y);
	}

	@Override
	public Node createNodeWithCoordinateAndOccupant(int x, int y, String occupantPlayerId) {
		return new BasicNode(x, y, occupantPlayerId);
	}
}
