package kth.game.othello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.BasicBoardCreator;
import kth.game.othello.board.BasicNodeCreator;
import kth.game.othello.board.Board;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.movestrategy.FirstAvailableMoveStrategy;
import kth.game.othello.player.movestrategy.GreedyMoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;

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

	@Test
	public void testComputerGameWithComputersSwappingStrategyOnDiamondBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(new ComputerPlayer("1", "1", new GreedyMoveStrategy()));
		players.add(new ComputerPlayer("2", "2", new RandomMoveStrategy()));
		players.add(new ComputerPlayer("3", "3", new FirstAvailableMoveStrategy()));
		Othello othello = getOthelloFactory()
				.createGame(
						new BoardFactory(new BasicNodeCreator(), new BasicBoardCreator()).getDiamondBoard(players, 13),
						players);
		othello.start(othello.getPlayers().get(0).getId());
		for (int i = 0; othello.isActive(); i++) {
			othello.move();
			if (i % 10 == 0) {
				othello.getPlayers().get(0).setMoveStrategy(new FirstAvailableMoveStrategy());
				othello.getPlayers().get(1).setMoveStrategy(new GreedyMoveStrategy());
				othello.getPlayers().get(2).setMoveStrategy(new RandomMoveStrategy());
			}
			int numberOfOccupiedNodes = getNumberOfOccupiedNodes(othello);
			int scoreSum = 0;
			for (Player player : othello.getPlayers()) {
				scoreSum += othello.getScore().getPoints(player.getId());
			}
			assertEquals(numberOfOccupiedNodes, scoreSum);
		}
	}
}
