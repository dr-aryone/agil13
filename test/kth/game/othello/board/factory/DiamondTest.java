package kth.game.othello.board.factory;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCreator;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class DiamondTest {

	private List<Player> getPlayers(int numberOfPlayers) {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(Mockito.mock(Player.class));
		}
		return players;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowExceptionIfFourPlayers() {
		Diamond diamond = new Diamond(null, null);
		int boardSize = 7;
		diamond.getDiamondBoard(getPlayers(4), boardSize);
		Assert.fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowExceptionIfOnlyTwoPlayers() {
		Diamond diamond = new Diamond(null, null);
		int boardSize = 7;
		diamond.getDiamondBoard(getPlayers(2), boardSize);
		Assert.fail();
	}

	@Test
	public void testThatABoardIsCreatedIfTheNumberOfPlayersAreThree() {
		NodeCreator nodeCreator = Mockito.mock(NodeCreator.class);
		Mockito.when(nodeCreator.createNodeWithCoordinate(Mockito.anyInt(), Mockito.anyInt())).thenReturn(null);
		BoardCreator boardCreator = Mockito.mock(BoardCreator.class);
		Mockito.when(boardCreator.createBoard(Mockito.anyListOf(Node.class))).thenReturn(null);
		Diamond diamond = new Diamond(nodeCreator, boardCreator);
		int boardSize = 7;

		diamond.getDiamondBoard(getPlayers(3), boardSize);

		Mockito.verify(boardCreator).createBoard(Mockito.anyListOf(Node.class));
	}
}
