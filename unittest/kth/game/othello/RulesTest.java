package kth.game.othello;

import java.util.Arrays;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class RulesTest extends MockingBase {

	@Test
	public void testSimpleGetNodesToSwap() {
		BoardHandler boardHandler = Mockito.mock(BoardHandler.class);
		Node xy00 = createMockedNode(0, 0, "0", null);
		Node xy01 = createMockedNode(0, 1, "1", "p2");
		Node xy02 = createMockedNode(0, 2, "2", "p1");
		Mockito.when(boardHandler.getNode("0")).thenReturn(xy00);
		Mockito.when(boardHandler.getNode("1")).thenReturn(xy01);
		Mockito.when(boardHandler.getNode("2")).thenReturn(xy02);
		Mockito.when(boardHandler.getNode(0, 1)).thenReturn(xy01);
		Mockito.when(boardHandler.getNode(0, 2)).thenReturn(xy02);
		Mockito.when(boardHandler.hasNode(0, 1)).thenReturn(true);
		Mockito.when(boardHandler.hasNode(0, 2)).thenReturn(true);

		Rules rules = new BasicRules(boardHandler);
		List<Node> swapped = rules.getNodesToSwap("p1", "0");

		Assert.assertEquals("1", swapped.get(0).getId());
		Assert.assertEquals("p2", swapped.get(0).getOccupantPlayerId());
	}

	@Test
	public void testIsMoveValid() {
		BoardHandler boardHandler = Mockito.mock(BoardHandler.class);
		Node xy11 = createMockedNode(1, 1, "0", null);
		Node xy21 = createMockedNode(2, 1, "1", "p2");
		Node xy31 = createMockedNode(3, 1, "2", "p1");
		Mockito.when(boardHandler.getNode("0")).thenReturn(xy11);
		Mockito.when(boardHandler.getNode("1")).thenReturn(xy21);
		Mockito.when(boardHandler.getNode("2")).thenReturn(xy31);
		Mockito.when(boardHandler.getNode(2, 1)).thenReturn(xy21);
		Mockito.when(boardHandler.getNode(3, 1)).thenReturn(xy31);
		Mockito.when(boardHandler.hasNode(2, 1)).thenReturn(true);
		Mockito.when(boardHandler.hasNode(3, 1)).thenReturn(true);
		Board board = Mockito.mock(Board.class);
		Mockito.when(board.getNodes()).thenReturn(Arrays.asList(xy11, xy21, xy31));
		Mockito.when(boardHandler.getBoard()).thenReturn(board);

		Rules rules = new BasicRules(boardHandler);

		Assert.assertTrue(rules.isMoveValid("p1", "0"));
	}

	@Test
	public void testHasValidMove() {
		BoardHandler boardHandler = Mockito.mock(BoardHandler.class);
		Node xy45 = createMockedNode(4, 5, "0", null);
		Node xy44 = createMockedNode(4, 4, "1", "p2");
		Node xy43 = createMockedNode(4, 3, "2", "p1");
		Mockito.when(boardHandler.getNode("0")).thenReturn(xy45);
		Mockito.when(boardHandler.getNode("1")).thenReturn(xy44);
		Mockito.when(boardHandler.getNode("2")).thenReturn(xy43);
		Mockito.when(boardHandler.getNode(4, 4)).thenReturn(xy44);
		Mockito.when(boardHandler.getNode(4, 3)).thenReturn(xy43);
		Mockito.when(boardHandler.hasNode(4, 4)).thenReturn(true);
		Mockito.when(boardHandler.hasNode(4, 3)).thenReturn(true);
		Board board = Mockito.mock(Board.class);
		Mockito.when(board.getNodes()).thenReturn(Arrays.asList(xy45, xy44, xy43));
		Mockito.when(boardHandler.getBoard()).thenReturn(board);

		Rules rules = new BasicRules(boardHandler);

		Assert.assertTrue(rules.hasValidMove("p1"));
		Assert.assertFalse(rules.hasValidMove("p2"));
	}
}
