package kth.game.othello.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import kth.game.othello.board.BasicBoard;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import org.junit.Test;

public class BoardTest extends TestCase {

	private Node createMockedNode(int x, int y) {
		Node node = mock(Node.class);
		when(node.getId()).thenReturn("node" + x + "," + y);
		when(node.getOccupantPlayerId()).thenReturn("player1");
		when(node.getXCoordinate()).thenReturn(x);
		when(node.getYCoordinate()).thenReturn(y);
		return node;
	}

	@Test
	public void testBoardCreation() {
		List<Node> boardNodes = new ArrayList<>();
		List<Node> sortedNodes = new ArrayList<>();

		for (int node = 0; node < 10; node++) {
			boardNodes.add(createMockedNode(9 - node, 9 - node));
			sortedNodes.add(createMockedNode(node, node));
		}
		Board board = new BasicBoard(boardNodes);
		boardNodes = board.getNodes();
		for (int node = 0; node < 10; node++) {
			Node boardNode = boardNodes.get(node);
			Node sortedNode = sortedNodes.get(node);
			assertEquals(sortedNode.getXCoordinate(), boardNode.getXCoordinate());
			assertEquals(sortedNode.getYCoordinate(), boardNode.getYCoordinate());
			assertEquals(sortedNode.getId(), boardNode.getId());
		}
	}
}
