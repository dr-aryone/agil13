package kth.game.othello.player;

/**
 * 
 * An implementation of the {@link Player} interface, where the type of the
 * player is {@link Type#HUMAN}.
 * 
 */
public class HumanPlayer extends AbstractPlayer {

	/**
	 * Create a new human player with the given ID and name.
	 * 
	 * @param id
	 *            The ID of the player
	 * @param name
	 *            The name of the player
	 */
	public HumanPlayer(String id, String name) {
		super(id, name, Player.Type.HUMAN);
	}

}
