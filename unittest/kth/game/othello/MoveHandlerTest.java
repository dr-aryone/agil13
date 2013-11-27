package kth.game.othello;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MoveHandlerTest extends MockingBase {

	@Test
	public void testGetPlayerInTurnWhenPlayerHasMove() {
		Player playerWhoHasMove = mock(Player.class);
		when(playerWhoHasMove.getId()).thenReturn("1");

		Rules rules = mock(Rules.class);
		when(rules.hasValidMove("1")).thenReturn(true);

		PlayerHandler playerHandler = mock(PlayerHandler.class);

		when(playerHandler.getPlayerInTurn()).thenReturn(playerWhoHasMove);

		BoardHandler boardHandler = mock(BoardHandler.class);

		MoveHandler moveHandler = new MoveHandler(rules, playerHandler, boardHandler);

		Assert.assertEquals("1", moveHandler.getPlayerInTurn().getId());
	}

	@Test
	public void testGetPlayerInTurnWhenPlayerHasNoMove() {
		Rules rules = mock(Rules.class);
		Player playerWhoHasNoMove = mock(Player.class);
		when(playerWhoHasNoMove.getId()).thenReturn("1");
		when(rules.hasValidMove("1")).thenReturn(false);

		final Player playerWhoHasMove = mock(Player.class);
		when(playerWhoHasMove.getId()).thenReturn("2");
		when(rules.hasValidMove("2")).thenReturn(true);

		final PlayerHandler playerHandler = mock(PlayerHandler.class);

		when(playerHandler.getPlayerInTurn()).thenReturn(playerWhoHasNoMove);
		when(playerHandler.getNextPlayer()).thenReturn(playerWhoHasMove);

		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				when(playerHandler.getPlayerInTurn()).thenReturn(playerWhoHasMove);
				return null;
			}
		}).when(playerHandler).changePlayer();

		MoveHandler moveHandler = new MoveHandler(rules, playerHandler, null);

		String result = moveHandler.getPlayerInTurn().getId();

		Assert.assertEquals("2", result);
	}

	@Test
	public void testGetPlayerInTurnWithThreePlayers() {
		Rules rules = mock(Rules.class);
		final Player firstPlayerWhoHasNoMove = mock(Player.class);
		when(firstPlayerWhoHasNoMove.getId()).thenReturn("1");
		when(rules.hasValidMove("1")).thenReturn(false);

		final Player secondPlayerWhoHasNoMove = mock(Player.class);
		when(secondPlayerWhoHasNoMove.getId()).thenReturn("2");
		when(rules.hasValidMove("2")).thenReturn(false);

		final Player playerWhoHasMove = mock(Player.class);
		when(playerWhoHasMove.getId()).thenReturn("3");
		when(rules.hasValidMove("3")).thenReturn(true);

		final PlayerHandler playerHandler = mock(PlayerHandler.class);

		when(playerHandler.getPlayerInTurn()).thenReturn(firstPlayerWhoHasNoMove);
		when(playerHandler.getNextPlayer()).thenReturn(secondPlayerWhoHasNoMove);

		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				if (playerHandler.getPlayerInTurn().getId().equals("1")) {
					when(playerHandler.getPlayerInTurn()).thenReturn(secondPlayerWhoHasNoMove);
					when(playerHandler.getNextPlayer()).thenReturn(playerWhoHasMove);
				} else if (playerHandler.getPlayerInTurn().getId().equals("2")) {
					when(playerHandler.getPlayerInTurn()).thenReturn(playerWhoHasMove);
					when(playerHandler.getNextPlayer()).thenReturn(firstPlayerWhoHasNoMove);
				} else if (playerHandler.getPlayerInTurn().getId().equals("3")) {
					when(playerHandler.getPlayerInTurn()).thenReturn(firstPlayerWhoHasNoMove);
					when(playerHandler.getNextPlayer()).thenReturn(secondPlayerWhoHasNoMove);
				}
				return null;
			}
		}).when(playerHandler).changePlayer();

		MoveHandler moveHandler = new MoveHandler(rules, playerHandler, null);

		String result = moveHandler.getPlayerInTurn().getId();

		Assert.assertEquals("3", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMoveWhenMoveIsInvalid() {
		Rules rules = mock(Rules.class);
		when(rules.isMoveValid("1", "1")).thenReturn(false);
		MoveHandler moveHandler = new MoveHandler(rules, null, null);

		moveHandler.move("1", "1");
		Assert.fail();
	}

	@Test
	public void testMoveReturnsCorrectListWhenMoveIsValid() {
		Rules rules = mock(Rules.class);
		when(rules.isMoveValid("1", "C")).thenReturn(true);
		Node chosenNode = mock(Node.class);
		when(chosenNode.getId()).thenReturn("C");

		Node swapNode = mock(Node.class);
		when(swapNode.getId()).thenReturn("B");

		when(rules.getNodesToSwap("1", "C")).thenReturn(new ArrayList<Node>(Arrays.asList(swapNode)));

		BoardHandler boardHandler = mock(BoardHandler.class);

		when(boardHandler.getNode("C")).thenReturn(chosenNode);

		PlayerHandler playerHandler = mock(PlayerHandler.class);

		MoveHandler moveHandler = new MoveHandler(rules, playerHandler, boardHandler);

		List<Node> result = moveHandler.move("1", "C");

		Assert.assertEquals(Arrays.asList(swapNode, chosenNode), result);
	}
}
