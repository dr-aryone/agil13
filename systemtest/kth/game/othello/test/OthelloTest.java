package kth.game.othello.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import kth.game.othello.BasicOthelloFactory;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

import org.junit.Test;

public class OthelloTest extends BaseTestCase {

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
		othello.start();
		List<Node> nodes = othello.getBoard().getNodes();
		for (Node node : nodes)
			if (node.getXCoordinate() >= 3 && node.getXCoordinate() <= 4) {
				if (node.getYCoordinate() >= 3 && node.getYCoordinate() <= 4)
					assertTrue(node.isMarked());
			} else
				assertFalse(node.isMarked());
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
			if (node.getXCoordinate() == 3 && node.getYCoordinate() == 3) {
				swapNode = node;
			}
		}

		List<Node> moves = othello.getNodesToSwap(othello.getPlayerInTurn().getId(), nodeToPlace.getId());

		assertEquals(moves.size(), 1);
		assertTrue(moves.contains(swapNode));
	}

	@Test
	public void testMove() {
		Othello othello = othelloFactory.createHumanGameOnOriginalBoard();
		othello.start("1");
		Node nodeToPlace = null, swapNode = null;
		for (Node node : othello.getBoard().getNodes()) {
			if (node.getXCoordinate() == 3 && node.getYCoordinate() == 2) {
				nodeToPlace = node;
			}
			if (node.getXCoordinate() == 3 && node.getYCoordinate() == 3) {
				swapNode = node;
			}
		}

		List<Node> moves = othello.move(othello.getPlayerInTurn().getId(), nodeToPlace.getId());
		assertEquals(moves.size(), 2);
		assertTrue(moves.contains(nodeToPlace));
		assertTrue(moves.contains(swapNode));
	}

	@Test
	public void someMovesBetweenAComputerAndHumanTest() {
		Othello othello = getOthelloFactory().createHumanVersusComputerGameOnOriginalBoard();
		Player human = null;
		if (othello.getPlayers().get(0).getType() == Type.COMPUTER) {
			human = othello.getPlayers().get(1);
		} else {
			human = othello.getPlayers().get(0);
		}
		othello.start(human.getId());
		makeAHumanMove(othello, human);
		othello.move();
		makeAHumanMove(othello, human);
		othello.move();
		makeAHumanMove(othello, human);
		othello.move();
		assertEquals(10, getNumberOfOccupiedNodes(othello));
	}

	@Test
	public void twoComputerOnAClassicalBoardTest() {
		Othello othello = getOthelloFactory().createComputerGameOnClassicalBoard();
		othello.start(othello.getPlayers().get(0).getId());
		while (othello.isActive()) {
			assertEquals(Type.COMPUTER, othello.getPlayerInTurn().getType());
			othello.move();
		}
	}

	@Test
	public void testIsActive() {
		Othello othello = othelloFactory.createComputerGameOnClassicalBoard();
		othello.start();
		while (othello.isActive())
			othello.move();
		assertFalse(othello.isActive());
	}

	@Test
	public void testComputerVersusComputer() {
		runOthello(othelloFactory.createComputerGameOnClassicalBoard());
	}

	@Test
	public void testComputerVersusHuman() {
		runOthello(othelloFactory.createHumanVersusComputerGameOnOriginalBoard());
	}

	@Test
	public void testHumanVersusHuman() {
		runOthello(othelloFactory.createHumanGameOnOriginalBoard());
	}

	private void runOthello(Othello othello) {
		int nodesMarked = 4;
		othello.start();
		while (othello.isActive()) {
			if (othello.getPlayerInTurn().getType() == Player.Type.COMPUTER) {
				if (!othello.move().isEmpty())
					nodesMarked++;
			} else if (othello.getPlayerInTurn().getType() == Player.Type.HUMAN) {
				if (!makeAHumanMove(othello, othello.getPlayerInTurn()).isEmpty())
					nodesMarked++;
			} else {
				throw new IllegalStateException("Player of unknown type");
			}
			assertEquals(nodesMarked, getNumberOfOccupiedNodes(othello));
		}
	}
}
