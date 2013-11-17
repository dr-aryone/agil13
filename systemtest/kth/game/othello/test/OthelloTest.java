package kth.game.othello.test;

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
	public void testBrickSwap() {
		Othello othello = othelloFactory.createHumanGameOnOriginalBoard();
		Player white = othello.getPlayers().get(0);
		Player black = othello.getPlayers().get(1);
		othello.start(black.getId());
		assertEquals(othello.getPlayerInTurn(), black);

		Node n1 = getNode(2, 3, othello);
		othello.move(black.getId(), n1.getId());
		assertEquals(getNode(2, 3, othello).getOccupantPlayerId(), black.getId());
		assertEquals(getNode(3, 3, othello).getOccupantPlayerId(), black.getId());
		assertEquals(getNode(4, 3, othello).getOccupantPlayerId(), black.getId());
		assertEquals(getNode(4, 4, othello).getOccupantPlayerId(), white.getId());

	}

	private Node getNode(int x, int y, Othello othello) {
		for (Node node : othello.getBoard().getNodes())
			if (node.getXCoordinate() == x && node.getYCoordinate() == y)
				return node;
		return null;
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
			if (othello.getPlayerInTurn().getType() == Player.Type.COMPUTER)
				othello.move().isEmpty();
			else if (othello.getPlayerInTurn().getType() == Player.Type.HUMAN)
				makeAHumanMove(othello, othello.getPlayerInTurn());

			nodesMarked++;
			assertEquals(nodesMarked, getNumberOfOccupiedNodes(othello));
		}
	}
}
