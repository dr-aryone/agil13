package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.player.Player;

/**
 * The responsibility of an OthelloCreator is to create Othello instances.
 * 
 */
public interface OthelloCreator {

	/**
	 * Create a new {@link Othello} instance.
	 * 
	 * @param board
	 *            The board to play Othello on
	 * @param players
	 *            The players participating in the game of Othello
	 * @return An Othello instance
	 */
	public Othello createOthello(Board board, List<Player> players);

}
