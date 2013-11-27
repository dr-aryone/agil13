package kth.game.othello.player.movestrategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import kth.game.othello.MockingBase;
import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import org.junit.Test;

public class FirstAvailableMoveStrategyTest extends MockingBase {

	@Test
	public void testGetNameIsNotNull() {
		MoveStrategy firstAvailableMoveStrategy = new FirstAvailableMoveStrategy();
		assertNotNull(firstAvailableMoveStrategy.getName());
	}

	@Test
	public void testMoveReturnsNullWhenNoValidMoveIsAvailable() {
		Rules rules = mock(Rules.class);
		Board board = mock(Board.class);

		MoveStrategy firstAvailableMoveStrategy = new FirstAvailableMoveStrategy();
		assertNull(firstAvailableMoveStrategy.move("1", rules, board));
	}

	@Test
	public void testMoveReturnsNotNullWhenValidMoveIsAvailable() {
		Board board = mock(Board.class);

		Node firstAvailableNode = mock(Node.class);
		when(firstAvailableNode.getId()).thenReturn("A");
		when(board.getNodes()).thenReturn(Arrays.asList(firstAvailableNode));

		Rules rules = mock(Rules.class);

		when(rules.isMoveValid("1", "A")).thenReturn(true);

		MoveStrategy firstAvailableMoveStrategy = new FirstAvailableMoveStrategy();
		assertNotNull(firstAvailableMoveStrategy.move("1", rules, board));
	}

	@Test
	public void testMoveReturnsCorrectNodeWhenValidMoveIsAvailable() {
		Board board = mock(Board.class);

		Node firstNode = mock(Node.class);
		when(firstNode.getId()).thenReturn("A");
		Node firstAvailableNode = mock(Node.class);
		when(firstAvailableNode.getId()).thenReturn("B");
		Node secondAvailableNode = mock(Node.class);
		when(secondAvailableNode.getId()).thenReturn("C");
		when(board.getNodes()).thenReturn(Arrays.asList(firstNode, firstAvailableNode, secondAvailableNode));

		Rules rules = mock(Rules.class);

		when(rules.isMoveValid("1", "A")).thenReturn(false);
		when(rules.isMoveValid("1", "B")).thenReturn(true);
		when(rules.isMoveValid("1", "C")).thenReturn(true);

		MoveStrategy firstAvailableMoveStrategy = new FirstAvailableMoveStrategy();
		assertEquals("B", firstAvailableMoveStrategy.move("1", rules, board).getId());
	}
}
