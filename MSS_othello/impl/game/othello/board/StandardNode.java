package game.othello.board;

import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import kth.game.othello.board.Node;

/**
 * The responsibility of this class is to hold information about a node in
 * othello
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class StandardNode extends Observable implements Node {

	private final int x;
	private final int y;
	private String occupantPlayerId;
	private final UUID id;

	/**
	 * Create a new node
	 * 
	 * @param x the x coordinate of the node
	 * @param y the y coordinate of the node
	 * @param occupantPlayerId the player id of the player occupying the node,
	 * or null if no player occupies this node
	 */
	public StandardNode(int x, int y, String occupantPlayerId) {
		this.x = x;
		this.y = y;
		this.occupantPlayerId = occupantPlayerId;
		this.id = UUID.randomUUID();
	}

	/**
	 * 
	 * @param newOccupantPlayerId the id of the player to occupy this node
	 */
	public void mark(String newOccupantPlayerId) {
		if (!newOccupantPlayerId.equals(this.occupantPlayerId)) {
			String oldOccupantPlayerId = this.occupantPlayerId;
			this.occupantPlayerId = newOccupantPlayerId;
			super.setChanged();
			this.notifyObservers(oldOccupantPlayerId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.board.Node#addObserver(java.util.Observer)
	 */
	public void addObserver(Observer observer) {
		super.addObserver(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.board.Node#getId()
	 */
	public String getId() {
		return this.id.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.board.Node#getOccupantPlayerId()
	 */
	public String getOccupantPlayerId() {
		return this.occupantPlayerId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.board.Node#getXCoordinate()
	 */
	public int getXCoordinate() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.board.Node#getYCoordinate()
	 */
	public int getYCoordinate() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.board.Node#isMarked()
	 */
	public boolean isMarked() {
		return this.occupantPlayerId != null;
	}

	@Override
	public int hashCode() {
		return StandardNode.hashCode(x, y);
	}

	/**
	 * @return the hashcode the given x and y as represented in a node-object
	 */
	public static int hashCode(int x, int y) {
		return x + 9999 * y;
	}

	@Override
	public String toString() {
		return "OthelloNode [Id=" + this.id.toString() + ", OccupantPlayerId=" + this.occupantPlayerId
				+ ", coordinate={" + x + "," + y + "}, marked=" + (this.occupantPlayerId != null) + "]";
	}

}
