package kth.game.othello;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import kth.game.othello.board.Node;

public class MockingBase {

	protected Node createMockedNode(int x, int y) {
		return createMockedNode(x, y, "node" + x + "," + y, "player1");
	}

	protected Node createMockedNode(int x, int y, String id, String playerId) {
		Node node = mock(Node.class);
		when(node.getId()).thenReturn(id);
		when(node.getOccupantPlayerId()).thenReturn(playerId);
		when(node.getXCoordinate()).thenReturn(x);
		when(node.getYCoordinate()).thenReturn(y);
		return node;
	}
}
