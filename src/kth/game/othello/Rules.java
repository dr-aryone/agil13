package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Node;

/**
 * The responsibility of the Rules is to define when a player can make a move and in that case also determine what nodes
 * to swap at the board.
 * 
 * @author Tomas Ekholm
 */
public interface Rules {

	/**
	 * Returns the nodes that will be swapped for a move at the given nodeId.
	 * 
	 * @param playerId the id of the player making the move
	 * @param nodeId the id of the node where the move is made
	 * @return the list of nodes that will be swapped for the given move
	 */
	public List<Node> getNodesToSwap(String playerId, String nodeId);

	/**
	 * Determines if a player has any valid move.
	 * 
	 * @param playerId the id of the player
	 * @return true if the player has a valid move
	 */
	public boolean hasValidMove(String playerId);

	/**
	 * Determines if a player is allowed to make a move at the given node.
	 * 
	 * @param playerId the id of the player making the move
	 * @param nodeId the node id where the player wants to play
	 * @return true if the move is valid
	 */
	public boolean isMoveValid(String playerId, String nodeId);
}
