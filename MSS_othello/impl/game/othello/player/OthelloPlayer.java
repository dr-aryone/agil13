package game.othello.player;

import java.util.UUID;

import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * Represents a player playing othello
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class OthelloPlayer implements Player {

	// Type of the player
	private final Type type;
	// Player name
	private String name;
	// Player id
	private final UUID id;

	MoveStrategy moveStrategy;

	/**
	 * Create a new player
	 * 
	 * @param name the name of the player
	 * @param type the type of the player
	 */
	public OthelloPlayer(String name, Type type) {
		this.type = type;
		this.id = UUID.randomUUID();
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.player.Player#getId()
	 */
	public String getId() {
		return this.id.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.player.Player#getName()
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.player.Player#getType()
	 */
	public Type getType() {
		return this.type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.player.Player#getMoveStrategy()
	 */
	public MoveStrategy getMoveStrategy() {
		if (this.type == Player.Type.HUMAN) {
			throw new UnsupportedOperationException();
		} else if (this.type == Player.Type.COMPUTER && moveStrategy == null) {
			throw new IllegalStateException("Computer player has no set strategy");
		}
		return moveStrategy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * kth.game.othello.player.Player#setMoveStrategy(kth.game.othello.player
	 * .movestrategy.MoveStrategy)
	 */
	public void setMoveStrategy(MoveStrategy moveStrategy) {
		if (this.type == Player.Type.HUMAN) {
			throw new UnsupportedOperationException();
		}
		this.moveStrategy = moveStrategy;
	}

	@Override
	public String toString() {
		return this.type + " " + this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OthelloPlayer other = (OthelloPlayer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
