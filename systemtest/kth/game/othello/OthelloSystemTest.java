package kth.game.othello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

import org.junit.Test;

public class OthelloSystemTest extends BaseTestCase {

	@Test
	public void testInitialPlayer() {
		Othello humanVersusComputer = getOthelloFactory().createHumanVersusComputerGameOnOriginalBoard();
		List<Player> players = humanVersusComputer.getPlayers();
		assertEquals(Player.Type.COMPUTER, players.get(0).getType());
		assertEquals(Player.Type.HUMAN, players.get(1).getType());
	}

	@Test
	public void testInitialBricks() {
		Othello othello = getOthelloFactory().createComputerGameOnClassicalBoard();
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
		Othello othello = getOthelloFactory().createHumanGameOnOriginalBoard();
		othello.start(othello.getPlayers().get(1).getId());
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

		assertEquals(1, moves.size());
		assertTrue(moves.contains(swapNode));
	}

	@Test
	public void testMove() {
		Othello othello = getOthelloFactory().createHumanGameOnOriginalBoard();
		othello.start(othello.getPlayers().get(1).getId());
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
		assertEquals(2, moves.size());
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
		Othello othello = getOthelloFactory().createComputerGameOnClassicalBoard();
		othello.start();
		while (othello.isActive())
			othello.move();
		assertFalse(othello.isActive());
	}

	@Test
	public void testBrickSwap() {
		Othello othello = getOthelloFactory().createHumanGameOnOriginalBoard();
		Player white = othello.getPlayers().get(0);
		Player black = othello.getPlayers().get(1);
		othello.start(black.getId());

		// round 1
		assertEquals(othello.getPlayerInTurn(), black);
		assertNull(getNode(2, 3, othello).getOccupantPlayerId());
		Node n23 = getNode(2, 3, othello);
		othello.move(black.getId(), n23.getId());
		assertEquals(black.getId(), getNode(2, 3, othello).getOccupantPlayerId());
		assertEquals(black.getId(), getNode(3, 3, othello).getOccupantPlayerId());
		assertEquals(black.getId(), getNode(4, 3, othello).getOccupantPlayerId());

		assertEquals(white.getId(), getNode(4, 4, othello).getOccupantPlayerId());

		// round 2
		assertEquals(othello.getPlayerInTurn(), white);
		assertNull(getNode(2, 2, othello).getOccupantPlayerId());
		Node n22 = getNode(2, 2, othello);
		othello.move(white.getId(), n22.getId());
		assertEquals(white.getId(), getNode(2, 2, othello).getOccupantPlayerId());
		assertEquals(white.getId(), getNode(3, 3, othello).getOccupantPlayerId());
		assertEquals(white.getId(), getNode(4, 4, othello).getOccupantPlayerId());

		assertEquals(black.getId(), getNode(2, 3, othello).getOccupantPlayerId());

		// round 3
		assertEquals(othello.getPlayerInTurn(), black);
		assertNull(getNode(3, 2, othello).getOccupantPlayerId());
		Node n32 = getNode(3, 2, othello);
		othello.move(black.getId(), n32.getId());
		assertEquals(black.getId(), getNode(2, 3, othello).getOccupantPlayerId());
		assertEquals(black.getId(), getNode(3, 3, othello).getOccupantPlayerId());
		assertEquals(black.getId(), getNode(4, 3, othello).getOccupantPlayerId());
		assertEquals(black.getId(), getNode(3, 2, othello).getOccupantPlayerId());
		assertEquals(black.getId(), getNode(3, 4, othello).getOccupantPlayerId());

		assertEquals(white.getId(), getNode(2, 2, othello).getOccupantPlayerId());
		assertEquals(white.getId(), getNode(4, 4, othello).getOccupantPlayerId());

		// round 4
		assertEquals(othello.getPlayerInTurn(), white);
		assertNull(getNode(2, 4, othello).getOccupantPlayerId());
		Node n24 = getNode(2, 4, othello);
		othello.move(white.getId(), n24.getId());
		assertEquals(white.getId(), getNode(2, 4, othello).getOccupantPlayerId());
		assertEquals(white.getId(), getNode(3, 4, othello).getOccupantPlayerId());
		assertEquals(white.getId(), getNode(4, 4, othello).getOccupantPlayerId());
		assertEquals(white.getId(), getNode(2, 2, othello).getOccupantPlayerId());
		assertEquals(white.getId(), getNode(2, 3, othello).getOccupantPlayerId());

		assertEquals(black.getId(), getNode(3, 2, othello).getOccupantPlayerId());
		assertEquals(black.getId(), getNode(3, 3, othello).getOccupantPlayerId());
		assertEquals(black.getId(), getNode(4, 3, othello).getOccupantPlayerId());
	}

	private Node getNode(int x, int y, Othello othello) {
		for (Node node : othello.getBoard().getNodes())
			if (node.getXCoordinate() == x && node.getYCoordinate() == y)
				return node;
		return null;
	}

	@Test
	public void testComputerVersusComputer() {
		runOthello(getOthelloFactory().createComputerGameOnClassicalBoard());
	}

	@Test
	public void testComputerVersusHuman() {
		runOthello(getOthelloFactory().createHumanVersusComputerGameOnOriginalBoard());
	}

	@Test
	public void testHumanVersusHuman() {
		runOthello(getOthelloFactory().createHumanGameOnOriginalBoard());
	}

	private void runOthello(Othello othello) {
		int nodesMarked = 4;
		othello.start();
		Player previous = null;
		while (othello.isActive()) {
			assertPlayerSwap(previous, othello);
			previous = othello.getPlayerInTurn();

			if (othello.getPlayerInTurn().getType() == Player.Type.COMPUTER)
				othello.move().isEmpty();
			else if (othello.getPlayerInTurn().getType() == Player.Type.HUMAN)
				makeAHumanMove(othello, othello.getPlayerInTurn());

			nodesMarked++;
			assertEquals(nodesMarked, getNumberOfOccupiedNodes(othello));
		}
	}

	private void assertPlayerSwap(Player previous, Othello othello) {
		if (!othello.getPlayerInTurn().equals(previous)) // player was swapped
			return;

		// the same player played several rounds in a row
		// assert that the other player has no valid moves
		List<Player> players = othello.getPlayers();
		int playerIndex = players.indexOf(previous);
		Player otherPlayer = players.get((playerIndex + 1) % players.size());
		assertFalse(othello.hasValidMove(otherPlayer.getId()));
	}
}
