package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.player.Player;

public class BasicOthelloCreator implements OthelloCreator {

	@Override
	public Othello createOthello(Board board, List<Player> players) {
		return new BasicOthello(board, players);
	}
}
