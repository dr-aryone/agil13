package kth.game.othello.board.factory;

import kth.game.othello.MockingBase;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCreator;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CastleTest extends MockingBase {

	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowExceptionIfFourPlayers() {
		Castle castle = new Castle(null, null);
		castle.getBoard(createMockedPlayers(3));
		Assert.fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowExceptionIfOnlyTwoPlayers() {
		Castle castle = new Castle(null, null);
		castle.getBoard(createMockedPlayers(1));
		Assert.fail();
	}

	@Test
	public void testThatABoardIsCreatedIfTheNumberOfPlayersAreThree() {
		NodeCreator nodeCreator = Mockito.mock(NodeCreator.class);
		Mockito.when(nodeCreator.createNodeWithCoordinate(Mockito.anyInt(), Mockito.anyInt())).thenReturn(null);
		BoardCreator boardCreator = Mockito.mock(BoardCreator.class);
		Mockito.when(boardCreator.createBoard(Mockito.anyListOf(Node.class))).thenReturn(null);
		Castle castle = new Castle(nodeCreator, boardCreator);

		castle.getBoard(createMockedPlayers(2));

		Mockito.verify(boardCreator).createBoard(Mockito.anyListOf(Node.class));
	}
}
