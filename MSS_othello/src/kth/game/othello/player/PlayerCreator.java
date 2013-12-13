package kth.game.othello.player;

import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * The responsibility of this entity is to create players.
 */
public interface PlayerCreator {

	/**
	 * @param name the name of the player
	 * @return a computer player with a standard move strategy
	 */
	public Player createComputerPlayer(String name);

	/**
	 * @param name the name of the player
	 * @param moveStrategy a move strategy
	 * @return a computer player with given move strategy
	 */
	public Player createComputerPlayer(String name, MoveStrategy moveStrategy);

	/**
	 * @param name the name of the player
	 * @return a human player
	 */
	public Player createHumanPlayer(String name);

}
