package kth.game.othello.player;

import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * 
 * An implementation of the {@link Player} interface, where the type of the
 * player is {@link Type#COMPUTER}.
 * 
 */
public class ComputerPlayer extends AbstractPlayer {

	/**
	 * Create a new computer player with the given ID and name.
	 * 
	 * @param id
	 *            The ID of the player.
	 * @param name
	 *            The name of the player.
	 * @param moveStrategy
	 *            The strategy the computer is going to use when playing.
	 */
	public ComputerPlayer(String id, String name, MoveStrategy moveStrategy) {
		super(id, name, Player.Type.COMPUTER, moveStrategy);
	}

}
