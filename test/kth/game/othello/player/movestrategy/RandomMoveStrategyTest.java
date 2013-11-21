package kth.game.othello.player.movestrategy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;

public class RandomMoveStrategyTest {

	@Test
	public void testHasName() {
		MoveStrategy strategy = new RandomMoveStrategy();
		Assert.assertNotNull(strategy.getName());
	}

	@Test
	public void testHasMoveWhenIsAvailable() {
		MoveStrategy strategy = new RandomMoveStrategy();
		Board board = mock(Board.class);
		List<Node> testNodes = new ArrayList<Node>();
		Rules rules = mock(Rules.class);

		testNodes.add(createMockedNode(0, 0, "1"));
		when(rules.isMoveValid("1", "0:0")).thenReturn(false);
		when(rules.isMoveValid("2", "0:0")).thenReturn(false);

		testNodes.add(createMockedNode(0, 1, "0"));
		when(rules.isMoveValid("1", "0:1")).thenReturn(false);
		when(rules.isMoveValid("2", "0:1")).thenReturn(false);

		testNodes.add(createMockedNode(1, 0, "0"));
		when(rules.isMoveValid("1", "1:0")).thenReturn(false);
		when(rules.isMoveValid("2", "1:0")).thenReturn(false);

		testNodes.add(createMockedNode(1, 1, "1"));
		when(rules.isMoveValid("1", "1:1")).thenReturn(false);
		when(rules.isMoveValid("2", "1:1")).thenReturn(false);

		testNodes.add(createMockedNode(2, 0, null));
		testNodes.add(createMockedNode(2, 1, null));

		when(board.getNodes()).thenReturn(testNodes);

		when(rules.isMoveValid("1", "2:0")).thenReturn(true);
		when(rules.isMoveValid("1", "2:1")).thenReturn(false);
		when(rules.isMoveValid("0", "2:0")).thenReturn(false);
		when(rules.isMoveValid("0", "2:1")).thenReturn(true);

		Node node = strategy.move("1", rules, board);

		Assert.assertTrue(node != null);
	}

	@Test
	public void testGivesNullWhenBoardIsFull() {
		MoveStrategy strategy = new RandomMoveStrategy();
		Board board = mock(Board.class);
		List<Node> testNodes = new ArrayList<Node>();
		Rules rules = mock(Rules.class);

		testNodes.add(createMockedNode(0, 0, "1"));
		when(rules.isMoveValid("1", "0:0")).thenReturn(false);
		when(rules.isMoveValid("2", "0:0")).thenReturn(false);

		testNodes.add(createMockedNode(0, 1, "0"));
		when(rules.isMoveValid("1", "0:1")).thenReturn(false);
		when(rules.isMoveValid("2", "0:1")).thenReturn(false);

		testNodes.add(createMockedNode(1, 0, "0"));
		when(rules.isMoveValid("1", "1:0")).thenReturn(false);
		when(rules.isMoveValid("2", "1:0")).thenReturn(false);

		testNodes.add(createMockedNode(1, 1, "1"));
		when(rules.isMoveValid("1", "1:1")).thenReturn(false);
		when(rules.isMoveValid("2", "1:1")).thenReturn(false);
		when(board.getNodes()).thenReturn(testNodes);

		Node node = strategy.move("1", rules, board);

		Assert.assertTrue(node == null);
	}

	@Test
	public void testGivesNullWhenNoValidMoveIsAvailable() {
		MoveStrategy strategy = new RandomMoveStrategy();
		Board board = mock(Board.class);
		List<Node> testNodes = new ArrayList<Node>();
		Rules rules = mock(Rules.class);

		testNodes.add(createMockedNode(0, 0, "1"));
		when(rules.isMoveValid("1", "0:0")).thenReturn(false);
		when(rules.isMoveValid("2", "0:0")).thenReturn(false);

		testNodes.add(createMockedNode(0, 1, "1"));
		when(rules.isMoveValid("1", "0:1")).thenReturn(false);
		when(rules.isMoveValid("2", "0:1")).thenReturn(false);

		testNodes.add(createMockedNode(1, 0, "0"));
		when(rules.isMoveValid("1", "1:0")).thenReturn(false);
		when(rules.isMoveValid("2", "1:0")).thenReturn(false);

		testNodes.add(createMockedNode(1, 1, "0"));
		when(rules.isMoveValid("1", "1:1")).thenReturn(false);
		when(rules.isMoveValid("2", "1:1")).thenReturn(false);
		when(board.getNodes()).thenReturn(testNodes);

		testNodes.add(createMockedNode(2, 0, null));
		testNodes.add(createMockedNode(2, 1, null));

		when(board.getNodes()).thenReturn(testNodes);

		when(rules.isMoveValid("1", "2:0")).thenReturn(false);
		when(rules.isMoveValid("1", "2:1")).thenReturn(false);
		when(rules.isMoveValid("0", "2:0")).thenReturn(false);
		when(rules.isMoveValid("0", "2:1")).thenReturn(false);

		Node node = strategy.move("1", rules, board);

		Assert.assertTrue(node == null);
	}

	private Node createMockedNode(int x, int y, String occupantPlayer) {
		Node node = mock(Node.class);
		when(node.getId()).thenReturn(x + ":" + y);
		when(node.getOccupantPlayerId()).thenReturn(occupantPlayer);
		when(node.getXCoordinate()).thenReturn(x);
		when(node.getYCoordinate()).thenReturn(y);
		return node;
	}
}
