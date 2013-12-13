package kth.game.othello.board;

import java.util.List;

/**
 * The responsibility of this entity is to create boards from nodes.
 */
public interface BoardCreator {

	/**
	 * Creates a board containing the given nodes.
	 * 
	 * @param nodes the nodes of the board
	 * @return the board
	 */
	public Board createBoard(List<Node> nodes);

}
