package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.BasicBoard;
import kth.game.othello.board.BasicNode;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;

public class BasicOthelloFactory implements OthelloFactory {

	private static final int OTHELLO_BOARD_SIDE_LENGTH = 8;
	private static final int MIDDLE_UPPER_LEFT_X = 3;
	private static final int MIDDLE_UPPER_LEFT_Y = MIDDLE_UPPER_LEFT_X;

	@Override
	public Othello createComputerGameOnClassicalBoard() {
		Player whitePlayer = new ComputerPlayer("0", "WHITE");
		Player blackPlayer = new ComputerPlayer("1", "BLACK");
		return createOthello(whitePlayer, blackPlayer);
	}

	@Override
	public Othello createHumanGameOnOriginalBoard() {
		Player whitePlayer = new HumanPlayer("0", "WHITE");
		Player blackPlayer = new HumanPlayer("1", "BLACK");
		return createOthello(whitePlayer, blackPlayer);
	}

	@Override
	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		Player computerPlayer = new ComputerPlayer("0", "WHITE");
		Player humanPlayer = new HumanPlayer("1", "BLACK");
		return createOthello(computerPlayer, humanPlayer);
	}

	private Othello createOthello(Player playerOne, Player playerTwo) {
		Board board = generateBoard();
		BasicOthello othello = new BasicOthello(board, playerOne, playerTwo);
		return othello;
	}

	private static Board generateBoard() {
		List<Node> nodes = new ArrayList<>();
		for (int x = 0; x < OTHELLO_BOARD_SIDE_LENGTH; x++) {
			for (int y = 0; y < OTHELLO_BOARD_SIDE_LENGTH; y++) {
				String id = "" + x + y;
				nodes.add(new BasicNode(x, y, id, null));
			}
		}
		return new BasicBoard(nodes);
	}
}
