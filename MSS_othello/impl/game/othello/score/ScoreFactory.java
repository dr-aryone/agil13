package game.othello.score;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

/**
 * The responsibility of this class is to create score objects
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class ScoreFactory {
	/**
	 * Create and return a score object
	 * 
	 * @param board
	 *            the board that the score object will listen on
	 * @param players
	 *            the players to keep score on
	 * @return the score object
	 */
	public Score createScore(Board board, List<Player> players) {
		Score_impl score = new Score_impl(board);
		score.addPlayers(players);

		for (Node node : board.getNodes()) {
			node.addObserver(score);
			if (node.isMarked()) {
				score.incrementScoreByOne(node.getOccupantPlayerId());
			}
		}
		return score;
	}
}
