package game.logic;

import game.othello.board.StandardBoard;
import game.othello.board.StandardNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * Implementing the logical rules of the game of Othello
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class StandardRuleStrategy extends Observable implements Rules {

	private StandardBoard board;

	public StandardRuleStrategy(Board board) {
		this.board = (StandardBoard) board;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Rules#hasValidMove(java.lang.String)
	 */
	public boolean hasValidMove(String playerId) {
		List<Node> nodes = board.getNodes();
		for (Node node : nodes) {
			if (isMoveValid(playerId, node.getId())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Rules#isMoveValid(java.lang.String,
	 * java.lang.String)
	 */
	public boolean isMoveValid(String playerId, String nodeId) {
		if (board.getNodeById(nodeId).isMarked())
			return false;
		List<Node> nodes = getNodesToSwap(playerId, nodeId);
		if (nodes.size() <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * Occupy a node and swap the occupant of found nodes in lines and diagonals
	 * 
	 * @param playerId the id of the player making the move
	 * @param nodeId the id of the node that is occupied by the player
	 * @return the list of nodes that have changed occupant
	 */
	public List<Node> move(String playerId, String nodeId) {
		List<Node> nodes = getNodesToSwap(playerId, nodeId);
		for (Node node : nodes) {
			((StandardNode) node).mark(playerId);
		}
		nodes.add(board.getNodeById(nodeId));
		board.getNodeById(nodeId).mark(playerId);
		this.nodesHasChanged(nodes);
		return nodes;
	}

	private void nodesHasChanged(List<Node> nodes) {
		this.setChanged();
		this.notifyObservers(nodes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Rules#getNodesToSwap(java.lang.String,
	 * java.lang.String)
	 */
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		List<Node> nodes = new ArrayList<Node>();

		nodes.addAll(this.nodesInLine(board, board.getNodeById(nodeId), playerId, 1, 0));
		nodes.addAll(this.nodesInLine(board, board.getNodeById(nodeId), playerId, -1, 0));
		nodes.addAll(this.nodesInLine(board, board.getNodeById(nodeId), playerId, 0, 1));
		nodes.addAll(this.nodesInLine(board, board.getNodeById(nodeId), playerId, 0, -1));
		nodes.addAll(this.nodesInLine(board, board.getNodeById(nodeId), playerId, 1, 1));
		nodes.addAll(this.nodesInLine(board, board.getNodeById(nodeId), playerId, -1, -1));
		nodes.addAll(this.nodesInLine(board, board.getNodeById(nodeId), playerId, 1, -1));
		nodes.addAll(this.nodesInLine(board, board.getNodeById(nodeId), playerId, -1, 1));

		return nodes;
	}

	/**
	 * Traverse a board according to the change in x- and y-direction. Stop when
	 * a node occupied by the specified player is found
	 * 
	 * @param board the board to traverse
	 * @param node the starting point of the line
	 * @param playerId the id of the player occupying the end node
	 * @param change_x the change in x-direction
	 * @param change_y the change in y-direction
	 * @return the nodes found between the startnode and the endnode
	 */
	private List<Node> nodesInLine(StandardBoard board, StandardNode node, String playerId, int change_x, int change_y) {
		int x = node.getXCoordinate() + change_x;
		int y = node.getYCoordinate() + change_y;

		ArrayList<Node> nodes = new ArrayList<Node>();

		while (board.getNode(x, y) != null && board.getNode(x, y).isMarked()
				&& !board.getNode(x, y).getOccupantPlayerId().equals(playerId)) {
			nodes.add(board.getNode(x, y));
			x += change_x;
			y += change_y;
		}

		if (board.getNode(x, y) == null || !board.getNode(x, y).isMarked()) {
			nodes.clear();
		}

		return nodes;
	}
}
