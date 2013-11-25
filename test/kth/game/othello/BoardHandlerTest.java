package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;

public class BoardHandlerTest extends MockingBase {

	@Test
	public void testGetNode() {
		Board board = createMockedBoard(5);
		BoardHandler boardHandler = new BoardHandler(board);
		Assert.assertEquals(0, boardHandler.getNode("0,0").getXCoordinate());
		Assert.assertEquals(0, boardHandler.getNode("0,0").getYCoordinate());
		Assert.assertEquals(1, boardHandler.getNode("1,1").getXCoordinate());
		Assert.assertEquals(1, boardHandler.getNode("1,1").getYCoordinate());
		Assert.assertEquals(2, boardHandler.getNode("2,2").getXCoordinate());
		Assert.assertEquals(2, boardHandler.getNode("2,2").getYCoordinate());
		Assert.assertEquals(3, boardHandler.getNode("3,3").getXCoordinate());
		Assert.assertEquals(3, boardHandler.getNode("3,3").getYCoordinate());
		Assert.assertEquals(4, boardHandler.getNode("4,4").getXCoordinate());
		Assert.assertEquals(4, boardHandler.getNode("4,4").getYCoordinate());

		Assert.assertNull(boardHandler.getNode("5,5"));
		Assert.assertNull(boardHandler.getNode("5,-1"));
		Assert.assertNull(boardHandler.getNode("-1,5"));
		Assert.assertNull(boardHandler.getNode("52312,53213123"));
	}

	@Test
	public void testOccupyNode() {
		List<Player> players = createMockedPlayers(2);
		Player white = players.get(0);
		Player black = players.get(1);
		Board board = createMockedBoard(5);
		BoardHandler boardHandler = new BoardHandler(board);

		Assert.assertNull(boardHandler.getNode("0,0").getOccupantPlayerId());
		boardHandler.occupyNodeByPlayer(boardHandler.getNode("0,0"), white);
		Assert.assertEquals("0", boardHandler.getNode("0,0").getOccupantPlayerId());

		Assert.assertNull(boardHandler.getNode("1,1").getOccupantPlayerId());
		boardHandler.occupyNodeByPlayer(boardHandler.getNode("1,1"), black);
		Assert.assertEquals("1", boardHandler.getNode("1,1").getOccupantPlayerId());

		Assert.assertNull(boardHandler.getNode("4,4").getOccupantPlayerId());
		boardHandler.occupyNodeByPlayer(boardHandler.getNode("4,4"), black);
		Assert.assertEquals("1", boardHandler.getNode("4,4").getOccupantPlayerId());
	}
}
