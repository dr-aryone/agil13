package kth.game.othello.board.factory;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import kth.game.othello.MockingBase;
import kth.game.othello.board.BasicBoardCreator;
import kth.game.othello.board.Board;
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

	@Test
	public void testBoardContainsNoDuplicates() {
		NodeCreator nodeCreator = mock(NodeCreator.class);
		int boardSize = 7;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				Node mockedNode = createMockedNode(i, j);
				when(nodeCreator.createNodeWithCoordinate(i, j)).thenReturn(mockedNode);
				mockedNode = createMockedNode(i, j, i + "," + j, "playerId");
				when(nodeCreator.createNodeWithCoordinateAndOccupant(Mockito.eq(i), Mockito.eq(j), Mockito.anyString()))
						.thenReturn(mockedNode);
			}
		}

		Castle castle = new Castle(nodeCreator, new BasicBoardCreator());
		Board board = castle.getBoard(createMockedPlayers(2));

		for (int i = 0; i < board.getNodes().size(); i++) {
			for (int j = 0; j < board.getNodes().size(); j++) {
				if (i == j)
					continue;

				Node node = board.getNodes().get(i);
				Node compareNode = board.getNodes().get(j);
				Assert.assertFalse((node.getXCoordinate() == compareNode.getXCoordinate() && node.getYCoordinate() == compareNode
						.getYCoordinate()));
			}
		}
	}
}
