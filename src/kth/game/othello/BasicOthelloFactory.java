package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.BasicBoard;
import kth.game.othello.board.BasicNode;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.Player;

class BasicOthelloFactory implements OthelloFactory {

	@Override
	public Othello createComputerGameOnClassicalBoard() {
		Player whitePlayer = new ComputerPlayer("0", "WHITE");
		Player blackPlayer = new ComputerPlayer("1", "BLACK");
		return new ClassicOthello(generateBoard(), whitePlayer, blackPlayer);
	}

	@Override
	public Othello createHumanGameOnOriginalBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Board generateBoard() {
		List<Node> nodes = new ArrayList<>();
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				String id = x * 10 + "" + y;
				nodes.add(new BasicNode(x, y, id, null));
			}
		}
		return new BasicBoard(nodes);
	}
}
