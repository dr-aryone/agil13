package DisplayStrategies;

import kth.game.othello.Othello;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * The responsibility of this class is to run the game of othello using the
 * graphical ui from OthelloView
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class ShowView implements RunPattern {

	private int timeBetweenSwaps;
	private int timeBetweenMoves;

	/**
	 * 
	 * @param timeBetweenSwaps the time between changing nodes
	 * @param timeBetweenMoves the time between player turns
	 */
	public ShowView(int timeBetweenSwaps, int timeBetweenMoves) {
		this.timeBetweenSwaps = timeBetweenSwaps;
		this.timeBetweenMoves = timeBetweenMoves;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see DisplayStrategies.RunPattern#runGame(kth.game.othello.Othello)
	 */
	public void runGame(Othello othello) {
		OthelloView othelloView = OthelloViewFactory.create(othello, timeBetweenSwaps, timeBetweenMoves);
		othelloView.start();
	}

}
