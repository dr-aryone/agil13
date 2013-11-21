package kth.game.othello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

import org.junit.Test;

public class OthelloLab2IT extends BaseTestCase {

	private void makeNumberOfComputerMoves(int numberOfMoves, Othello othello) {
		for (int i = 0; i < numberOfMoves; i++) {
			assertEquals(Type.COMPUTER, othello.getPlayerInTurn().getType());
			othello.move();
		}
	}

	@Test
	public void studyTheInitialScoreTest() {
		Othello othello = getOthelloFactory().createHumanVersusComputerGameOnOriginalBoard();
		String playerId = othello.getPlayers().get(0).getId();
		othello.start();
		assertEquals(2, othello.getScore().getPoints(playerId));
	}

	@Test
	public void studyTheScoreAfterAMoveTest() {
		Othello othello = getOthelloFactory().createHumanVersusComputerGameOnOriginalBoard();
		String playerId = othello.getPlayers().get(0).getId();
		othello.start(playerId);
		othello.move(playerId, othello.getBoard().getNode(5, 3).getId());
		assertEquals(4, othello.getScore().getPoints(playerId));
	}

	@Test
	public void threeComputersOnADiamondBoardTest() {
		BoardFactory boardFactory = getBoardFactory();
		List<Player> players = new ArrayList<Player>();
		players.add(getPlayerCreator().createComputerPlayer("black"));
		players.add(getPlayerCreator().createComputerPlayer("white"));
		players.add(getPlayerCreator().createComputerPlayer("orange"));
		int boardSize = 11;
		Board board = boardFactory.getDiamondBoard(players, boardSize);
		Othello othello = getOthelloFactory().createGame(board, players);
		othello.start();
		while (othello.isActive()) {
			othello.move();
		}

		assertFalse(othello.isActive());
	}

	@Test
	public void twoComputerOnAClassicalBoardTest() {
		Othello othello = getOthelloFactory().createComputerGameOnClassicalBoard();
		othello.start(othello.getPlayers().get(0).getId());

		// Make some moves
		makeNumberOfComputerMoves(10, othello);

		// Change one of the computers strategy
		othello.getPlayers().get(0).setMoveStrategy(getNewMoveStrategy());

		// Make some moves
		makeNumberOfComputerMoves(50, othello);

		assertFalse(othello.isActive());
	}
}
