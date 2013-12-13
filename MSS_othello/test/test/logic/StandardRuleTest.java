package test.logic;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import game.logic.StandardRuleStrategy;
import game.othello.board.StandardBoard;
import game.othello.board.StandardNode;
import game.othello.player.OthelloPlayer;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class StandardRuleTest {

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
	public void testMoveActuallyChange() {
		StandardBoard board = mock(StandardBoard.class);
		OthelloPlayer player1 = mock(OthelloPlayer.class);
		OthelloPlayer player2 = mock(OthelloPlayer.class);

		when(player1.getId()).thenReturn("1");
		when(player2.getId()).thenReturn("2");

		StandardNode node11 = mockNode(0, null, 1, 1);
		StandardNode node12 = mockNode(1, player2.getId(), 1, 2);
		StandardNode node13 = mockNode(2, player1.getId(), 1, 3);

		when(board.getNode(1, 1)).thenReturn(node11);
		when(board.getNode(1, 2)).thenReturn(node12);
		when(board.getNode(1, 3)).thenReturn(node13);

		when(board.getNodeById("0")).thenReturn(node11);
		when(board.getNodeById("1")).thenReturn(node12);
		when(board.getNodeById("2")).thenReturn(node13);

		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock inv) {
				Object[] args = inv.getArguments();
				StandardNode node = (StandardNode) inv.getMock();
				when(node.getOccupantPlayerId()).thenReturn((String) args[0]);
				return args[0];
			}
		}).when(node12).mark(anyString());

		StandardRuleStrategy standardRuleStrategy = new StandardRuleStrategy(board);
		standardRuleStrategy.move(player1.getId(), node11.getId());
		Assert.assertEquals(player1.getId(), board.getNode(1, 2).getOccupantPlayerId());
	}
}
