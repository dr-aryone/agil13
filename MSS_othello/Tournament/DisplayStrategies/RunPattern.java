package DisplayStrategies;

import kth.game.othello.Othello;

/**
 * The responsibility of this class is run a game of othello
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public interface RunPattern {

	/**
	 * Run the game from start to finish
	 * 
	 * @param ot
	 */
	public void runGame(Othello ot);

}