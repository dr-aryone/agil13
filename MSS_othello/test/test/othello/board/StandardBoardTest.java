package test.othello.board;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import game.othello.board.StandardBoard;
import game.othello.board.StandardNode;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;

public class StandardBoardTest {

	private StandardNode mockNode(int id, String occupantPlayerId, int x, int y) {
		StandardNode node = mock(StandardNode.class);
		when(node.getId()).thenReturn(id + "");
		when(node.getXCoordinate()).thenReturn(x);
		when(node.getYCoordinate()).thenReturn(y);
		if (occupantPlayerId == null) {
			when(node.getOccupantPlayerId()).thenReturn(null);
			when(node.isMarked()).thenReturn(false);
		} else {
			when(node.getOccupantPlayerId()).thenReturn(occupantPlayerId);
			when(node.isMarked()).thenReturn(true);
		}

		return node;
		// TODO Auto-generated method stub

	}

	@Test
	public void getByXYTest() {
		ArrayList<Node> nodes = new ArrayList<Node>();
		StandardNode node1 = mock(StandardNode.class);
		when(node1.getXCoordinate()).thenReturn(1);
		when(node1.getYCoordinate()).thenReturn(3);
		when(node1.getId()).thenReturn("HEJHEJ");
		nodes.add(node1);
		StandardNode node2 = mock(StandardNode.class);
		when(node2.getXCoordinate()).thenReturn(3);
		when(node2.getYCoordinate()).thenReturn(1);
		when(node2.getId()).thenReturn("JEHJEH");
		nodes.add(node2);
		StandardBoard board = new StandardBoard(nodes);
		Node n = board.getNode(1, 3);
		Assert.assertEquals(n.getId(), "HEJHEJ");
		n = board.getNode(3, 1);
		Assert.assertEquals(n.getId(), "JEHJEH");
	}

	@Test
	public void getIDtest() {
		ArrayList<Node> nodes = new ArrayList<Node>();
		StandardNode node1 = mock(StandardNode.class);
		when(node1.getXCoordinate()).thenReturn(1);
		when(node1.getYCoordinate()).thenReturn(3);
		when(node1.getId()).thenReturn("HEJHEJ");
		nodes.add(node1);
		StandardNode node2 = mock(StandardNode.class);
		when(node2.getXCoordinate()).thenReturn(3);
		when(node2.getYCoordinate()).thenReturn(1);
		when(node2.getId()).thenReturn("JEHJEH");
		nodes.add(node2);
		StandardBoard board = new StandardBoard(nodes);
		Node n = board.getNodeById("HEJHEJ");
		Assert.assertEquals(n.getXCoordinate(), 1);
		n = board.getNodeById("JEHJEH");
		Assert.assertEquals(n.getXCoordinate(), 3);
	}

	@Test
	public void getMaxXYlengths() {
		StandardNode node11 = mockNode(1, "", 1, 1);
		StandardNode node12 = mockNode(1, "", 1, 2);
		StandardNode node13 = mockNode(1, "", 1, 3);
		StandardNode node21 = mockNode(1, "", 2, 1);
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(node11);
		nodes.add(node12);
		nodes.add(node13);
		nodes.add(node21);
		StandardBoard board = new StandardBoard(nodes);
		Assert.assertEquals(2, board.getMaxX());
		Assert.assertEquals(3, board.getMaxY());
	}

}
