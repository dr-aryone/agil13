package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;

public class OthelloFactory {

	private BoardFactory boardFactory;
	private OthelloCreator othelloCreator;
	private PlayerCreator playerCreator;

	public OthelloFactory(OthelloCreator othelloCreator, BoardFactory boardFactory, PlayerCreator playerCreator) {
		this.othelloCreator = othelloCreator;
		this.boardFactory = boardFactory;
		this.playerCreator = playerCreator;
	}

	/*
	 * (non-Javadoc)
	 * @see kth.game.othello.OthelloFactory#createComputerGameOnClassicalBoard()
	 */
	public Othello createComputerGameOnClassicalBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(playerCreator.createComputerPlayer("Computer"));
		players.add(playerCreator.createComputerPlayer("computer"));
		return othelloCreator.createOthello(boardFactory.getQuadraticBoard(8, players), players);
	}

	/*
	 * (non-Javadoc)
	 * @see kth.game.othello.OthelloFactory#createGame(kth.game.othello.board.Board, java.util.List)
	 */
	public Othello createGame(Board board, List<Player> players) {
		return othelloCreator.createOthello(board, players);
	}

	/*
	 * (non-Javadoc)
	 * @see kth.game.othello.OthelloFactory#createHumanGameOnOriginalBoard()
	 */
	public Othello createHumanGameOnOriginalBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(playerCreator.createHumanPlayer("HUMAN"));
		players.add(playerCreator.createHumanPlayer("human"));
		Board board = boardFactory.getQuadraticBoard(8, players);
		return othelloCreator.createOthello(board, players);
	}

	/*
	 * (non-Javadoc)
	 * @see kth.game.othello.OthelloFactory#createHumanVersusComputerGameOnOriginalBoard()
	 */
	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(playerCreator.createComputerPlayer("Computer"));
		players.add(playerCreator.createHumanPlayer("Human"));
		Board board = boardFactory.getQuadraticBoard(8, players);
		return othelloCreator.createOthello(board, players);
	};

}
