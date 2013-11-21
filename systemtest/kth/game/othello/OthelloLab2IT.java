package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import kth.game.othello.board.Board;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.movestrategy.MoveStrategy;

import org.junit.Test;

public class OthelloLab2IT {

	private BoardFactory getBoardFactory() {
		return null;
	}

	private MoveStrategy getNewMoveStrategy() {
		return null;
	}

	private OthelloFactory getOthelloFactory() {
		return null;
	}

	private PlayerCreator getPlayerCreator() {
		return null;
	}

	private void makeNumberOfComputerMoves(int numberOfMoves, Othello othello) {
		for (int i = 0; i < numberOfMoves; i++) {
			Assert.assertEquals(Type.COMPUTER, othello.getPlayerInTurn().getType());
			othello.move();
		}
	}

	@Test
	public void studyTheInitialScoreTest() {
		Othello othello = getOthelloFactory().createHumanVersusComputerGameOnOriginalBoard();
		String playerId = othello.getPlayers().get(0).getId();
		othello.start();
		Assert.assertEquals(2, othello.getScore().getPoints(playerId));
	}

	@Test
	public void studyTheScoreAfterAMoveTest() {
		Othello othello = getOthelloFactory().createHumanVersusComputerGameOnOriginalBoard();
		String playerId = othello.getPlayers().get(0).getId();
		othello.start(playerId);
		othello.move(playerId, othello.getBoard().getNode(5, 3).getId());
		Assert.assertEquals(4, othello.getScore().getPoints(playerId));
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
		System.out.println(board.toString());
		Othello othello = getOthelloFactory().createGame(board, players);
		othello.start();
		while (othello.isActive()) {
			othello.move();
		}

		Assert.assertFalse(othello.isActive());
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

		Assert.assertFalse(othello.isActive());
	}
}
