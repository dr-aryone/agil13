package kth.game.othello.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import junit.framework.TestCase;
import kth.game.othello.BasicOthelloFactory;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import org.junit.Test;

public class OthelloTest extends TestCase {

	static final OthelloFactory othelloFactory = new BasicOthelloFactory();

	private Player createMockedPlayer(int playerIndex) {
		Player player = mock(Player.class);
		when(player.getId()).thenReturn("player" + playerIndex);
		when(player.getName()).thenReturn("Player " + playerIndex);
		return player;
	}

	@Test
	public void testInitialPlayer() {
		Othello humanVersusComputer = othelloFactory.createHumanVersusComputerGameOnOriginalBoard();
		List<Player> players = humanVersusComputer.getPlayers();
		assertEquals(Player.Type.COMPUTER, players.get(0).getType());
		assertEquals(Player.Type.HUMAN, players.get(1).getType());
	}

	@Test
	public void testInitialBricks() {
		Othello othello = othelloFactory.createComputerGameOnClassicalBoard();
		List<Node> nodes = othello.getBoard().getNodes();
		assertTrue(nodes.get(3 * 8 + 3).isMarked());
		assertTrue(nodes.get(3 * 8 + 4).isMarked());
		assertTrue(nodes.get(4 * 8 + 3).isMarked());
		assertTrue(nodes.get(4 * 8 + 4).isMarked());
		assertFalse(nodes.get(5 * 8 + 3).isMarked());
		assertFalse(nodes.get(3 * 8 + 7).isMarked());
		assertFalse(nodes.get(1 * 8 + 3).isMarked());
		assertFalse(nodes.get(2 * 8 + 4).isMarked());
	}

	@Test
	public void testGetNodesToSwap() {
		Othello othello = othelloFactory.createHumanGameOnOriginalBoard();
		othello.start("1");
		Node nodeToPlace = null, swapNode = null;
		for (Node node : othello.getBoard().getNodes()) {
			if (node.getXCoordinate() == 3 && node.getYCoordinate() == 2) {
				nodeToPlace = node;
			}
			if (node.getXCoordinate() == 3 && node.getXCoordinate() == 3) {
				swapNode = node;
			}
		}

		List<Node> moves = othello.move(othello.getPlayerInTurn().getId(), nodeToPlace.getId());

		assertEquals(moves.size(), 2);
		assertTrue(moves.contains(nodeToPlace));
		assertTrue(moves.contains(swapNode));
	}
}
