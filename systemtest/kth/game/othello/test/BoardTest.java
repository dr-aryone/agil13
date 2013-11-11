package kth.game.othello.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import kth.game.othello.board.Node;

import org.junit.Test;

public class BoardTest extends TestCase {

	private Node createMockedNode(int x, int y) {
		Node node = mock(Node.class);
		when(node.getXCoordinate()).thenReturn(x);
		when(node.getYCoordinate()).thenReturn(y);
		return node;
	}

	@Test
	public void testBoardCreation() {

	}
}
