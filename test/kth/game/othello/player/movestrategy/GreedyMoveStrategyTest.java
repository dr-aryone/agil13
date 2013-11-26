package kth.game.othello.player.movestrategy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kth.game.othello.MockingBase;
import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;

public class GreedyMoveStrategyTest extends MockingBase {

	@Test
	public void testHasName() {
		MoveStrategy strategy = new RandomMoveStrategy();
		Assert.assertNotNull(strategy.getName());
	}

	@Test
	public void testGivesMostBricksFromAllAvailableMoves() {
		MoveStrategy strategy = new GreedyMoveStrategy();
		Board board = mock(Board.class);
		List<Node> testNodes = new ArrayList<Node>();

		Rules rules = mock(Rules.class);

		testNodes.add(createMockedNode(0, 0, "0:0", "1"));
		when(rules.isMoveValid("0", "0:0")).thenReturn(false);
		when(rules.isMoveValid("1", "0:0")).thenReturn(false);

		testNodes.add(createMockedNode(0, 1, "0:1", "0"));
		when(rules.isMoveValid("0", "0:1")).thenReturn(false);
		when(rules.isMoveValid("1", "0:1")).thenReturn(false);

		testNodes.add(createMockedNode(1, 0, "1:0", "0"));
		when(rules.isMoveValid("0", "1:0")).thenReturn(false);
		when(rules.isMoveValid("1", "1:0")).thenReturn(false);

		testNodes.add(createMockedNode(1, 1, "1:1", "1"));
		when(rules.isMoveValid("0", "1:1")).thenReturn(false);
		when(rules.isMoveValid("1", "1:1")).thenReturn(false);

		testNodes.add(createMockedNode(2, 0, "2:0", "0"));
		when(rules.isMoveValid("0", "2:0")).thenReturn(false);
		when(rules.isMoveValid("1", "2:0")).thenReturn(false);

		testNodes.add(createMockedNode(2, 1, "2:1", "0"));
		when(rules.isMoveValid("0", "2:1")).thenReturn(false);
		when(rules.isMoveValid("1", "2:1")).thenReturn(false);

		testNodes.add(createMockedNode(3, 0, "3:0", null));
		when(rules.isMoveValid("0", "3:0")).thenReturn(false);
		when(rules.isMoveValid("1", "3:0")).thenReturn(true);

		testNodes.add(createMockedNode(3, 1, "3:1", null));
		when(rules.isMoveValid("0", "3:1")).thenReturn(false);
		when(rules.isMoveValid("1", "3:1")).thenReturn(true);

		when(board.getNodes()).thenReturn(testNodes);

		List<Node> smallResult = Arrays.asList(createMockedNode(2, 1));
		when(rules.getNodesToSwap("1", "3:1")).thenReturn(smallResult);
		List<Node> bigResult = Arrays.asList(createMockedNode(1, 0), createMockedNode(2, 0));
		when(rules.getNodesToSwap("1", "3:0")).thenReturn(bigResult);

		Node node = strategy.move("1", rules, board);

		Assert.assertNotNull(node);
		Assert.assertEquals(3, node.getXCoordinate());
		Assert.assertEquals(0, node.getYCoordinate());
	}

	@Test
	public void testGivesNullWhenBoardIsFull() {
		MoveStrategy strategy = new GreedyMoveStrategy();
		Board board = mock(Board.class);
		List<Node> testNodes = new ArrayList<Node>();
		Rules rules = mock(Rules.class);

		testNodes.add(createMockedNode(0, 0, "0:0", "1"));
		when(rules.isMoveValid("1", "0:0")).thenReturn(false);

		testNodes.add(createMockedNode(0, 1, "0:1", "0"));
		when(rules.isMoveValid("0", "0:1")).thenReturn(false);
		when(rules.isMoveValid("1", "0:1")).thenReturn(false);

		testNodes.add(createMockedNode(1, 0, "1:0", "0"));
		when(rules.isMoveValid("0", "1:0")).thenReturn(false);
		when(rules.isMoveValid("1", "1:0")).thenReturn(false);

		testNodes.add(createMockedNode(1, 1, "1:1", "1"));
		when(rules.isMoveValid("0", "1:1")).thenReturn(false);
		when(rules.isMoveValid("1", "1:1")).thenReturn(false);

		when(board.getNodes()).thenReturn(testNodes);

		Node node = strategy.move("1", rules, board);

		Assert.assertNull(node);
	}

	@Test
	public void testGivesNullWhenNoValidMoveIsAvailable() {
		MoveStrategy strategy = new GreedyMoveStrategy();
		Board board = mock(Board.class);
		List<Node> testNodes = new ArrayList<Node>();
		Rules rules = mock(Rules.class);

		testNodes.add(createMockedNode(0, 0, "0:0", "1"));
		when(rules.isMoveValid("0", "0:0")).thenReturn(false);
		when(rules.isMoveValid("1", "0:0")).thenReturn(false);

		testNodes.add(createMockedNode(0, 1, "0:1", "1"));
		when(rules.isMoveValid("0", "0:1")).thenReturn(false);
		when(rules.isMoveValid("1", "0:1")).thenReturn(false);

		testNodes.add(createMockedNode(1, 0, "1:0", "0"));
		when(rules.isMoveValid("0", "1:0")).thenReturn(false);
		when(rules.isMoveValid("1", "1:0")).thenReturn(false);

		testNodes.add(createMockedNode(1, 1, "1:1", "0"));
		when(rules.isMoveValid("0", "1:1")).thenReturn(false);
		when(rules.isMoveValid("1", "1:1")).thenReturn(false);
		when(board.getNodes()).thenReturn(testNodes);

		testNodes.add(createMockedNode(2, 0, "2:0", null));
		testNodes.add(createMockedNode(2, 1, "2:1", null));

		when(board.getNodes()).thenReturn(testNodes);

		when(rules.isMoveValid("1", "2:0")).thenReturn(false);
		when(rules.isMoveValid("1", "2:1")).thenReturn(false);
		when(rules.isMoveValid("0", "2:0")).thenReturn(false);
		when(rules.isMoveValid("0", "2:1")).thenReturn(false);

		Node node = strategy.move("1", rules, board);

		Assert.assertTrue(node == null);
	}
}
