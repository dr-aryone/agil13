package test.othello.board;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import game.othello.board.NodeComparator;
import game.othello.board.StandardNode;

import java.util.ArrayList;
import java.util.Collections;

import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;

public class NodeComparatorTest {

	@Test
	public void testSorting() {
		StandardNode mockedNode1 = mock(StandardNode.class);
		StandardNode mockedNode2 = mock(StandardNode.class);
		StandardNode mockedNode3 = mock(StandardNode.class);

		when(mockedNode1.getXCoordinate()).thenReturn(1);
		when(mockedNode1.getYCoordinate()).thenReturn(1);
		when(mockedNode1.getId()).thenReturn("0"); // Index in sorted list

		when(mockedNode2.getXCoordinate()).thenReturn(3);
		when(mockedNode2.getYCoordinate()).thenReturn(0);
		when(mockedNode2.getId()).thenReturn("2"); // Index in sorted list

		when(mockedNode3.getXCoordinate()).thenReturn(2);
		when(mockedNode3.getYCoordinate()).thenReturn(2);
		when(mockedNode3.getId()).thenReturn("1"); // Index in sorted list

		ArrayList<Node> nodes = new ArrayList<Node>();

		nodes.add(mockedNode3);
		nodes.add(mockedNode2);
		nodes.add(mockedNode1);

		Collections.sort(nodes, new NodeComparator());

		Assert.assertEquals("0", nodes.get(0).getId());
		Assert.assertEquals("1", nodes.get(1).getId());
		Assert.assertEquals("2", nodes.get(2).getId());
	}

	// Short method to simplify creation of mocked nodes
	private StandardNode mockNode(int x, int y) {
		StandardNode node = mock(StandardNode.class);
		when(node.getXCoordinate()).thenReturn(x);
		when(node.getYCoordinate()).thenReturn(y);
		when(node.getId()).thenReturn(null);
		return node;
	}

	@Test
	public void testEqual() {
		// setup test nodes
		StandardNode n1_11 = mockNode(1, 1);
		StandardNode n2_11 = mockNode(1, 1);
		StandardNode n3_11 = mockNode(1, 1);
		when(n3_11.getId()).thenReturn("hej");

		StandardNode n4_12 = mockNode(1, 2);
		StandardNode n5_13 = mockNode(1, 3);
		StandardNode n6_21 = mockNode(2, 1);

		NodeComparator nc = new NodeComparator();
		// Test that if they are equal they return 0
		Assert.assertTrue(nc.compare(n1_11, n2_11) == 0);
		Assert.assertTrue(nc.compare(n1_11, n3_11) == 0);
		Assert.assertTrue(nc.compare(n1_11, n1_11) == 0);

		// Test for transitivity and that larger/smaller than
		Assert.assertTrue(nc.compare(n1_11, n4_12) < 0);
		Assert.assertTrue(nc.compare(n4_12, n1_11) > 0);
		Assert.assertTrue(nc.compare(n4_12, n5_13) < 0);
		Assert.assertTrue(nc.compare(n4_12, n6_21) < 0);
		Assert.assertTrue(nc.compare(n6_21, n4_12) > 0);

	}
}
