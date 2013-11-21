package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.player.Player;

public interface OthelloCreator {

	public Othello createOthello(Board board, List<Player> players);

}
