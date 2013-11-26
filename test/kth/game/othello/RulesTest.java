package kth.game.othello;

import java.util.List;

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

		Rules rules = new BasicRules(boardHandler);
		List<Node> swapped = rules.getNodesToSwap("p1", "0");
		Assert.assertEquals("1", swapped.get(0).getId());
		Assert.assertEquals("p2", swapped.get(0).getOccupantPlayerId());
	}
}
