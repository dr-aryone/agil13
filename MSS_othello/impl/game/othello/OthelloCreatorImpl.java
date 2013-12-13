package game.othello;

import game.othello.score.ScoreFactory;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.OthelloCreator;
import kth.game.othello.board.Board;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

/**
 * The responsibility of this class is to create Othello
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class OthelloCreatorImpl implements OthelloCreator {

	public ScoreFactory scoreFactory = new ScoreFactory();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * kth.game.othello.OthelloCreator#createOthello(kth.game.othello.board.
	 * Board, java.util.List)
	 */
	public Othello createOthello(Board board, List<Player> players) {
		Score score = scoreFactory.createScore(board, players);
		return new OthelloTheGame(board, players, score);
	}
}
