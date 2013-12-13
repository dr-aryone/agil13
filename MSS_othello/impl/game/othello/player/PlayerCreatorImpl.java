package game.othello.player;

import game.othello.ai.NaiveAIStrategy;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * The responsibility of this class is to create OthelloPlayers
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class PlayerCreatorImpl implements PlayerCreator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * kth.game.othello.player.PlayerCreator#createComputerPlayer(java.lang.
	 * String)
	 */
	public Player createComputerPlayer(String name) {
		Player player = new OthelloPlayer(name, Player.Type.COMPUTER);
		player.setMoveStrategy(new NaiveAIStrategy());
		return player;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * kth.game.othello.player.PlayerCreator#createComputerPlayer(java.lang.
	 * String, kth.game.othello.player.movestrategy.MoveStrategy)
	 */
	public Player createComputerPlayer(String name, MoveStrategy moveStrategy) {
		Player player = createComputerPlayer(name);
		player.setMoveStrategy(moveStrategy);
		return player;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * kth.game.othello.player.PlayerCreator#createHumanPlayer(java.lang.String)
	 */
	public Player createHumanPlayer(String name) {
		return new OthelloPlayer(name, Player.Type.HUMAN);
	}

}
