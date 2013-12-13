package DisplayStrategies;

import kth.game.othello.Othello;

/**
 * The responsibility of this class is to run the game of othello in the
 * background
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class NoView implements RunPattern {

	/*
	 * (non-Javadoc)
	 * 
	 * @see DisplayStrategies.DisplayStrategy#runGame(kth.game.othello.Othello)
	 */
	public void runGame(Othello ot) {
		ot.start();
		while (ot.isActive()) {
			ot.move();
		}
	}
}
