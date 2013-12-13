package game.othello.board;

import game.othello.player.PlayerColour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * An Othello board that holds nodes where stones can be placed
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class StandardBoard implements Board {

	private final static String UNOWNED = ".";

	private HashMap<String, Node> nodesByID = new HashMap<String, Node>();
	private HashMap<Integer, Node> nodesByXY = new HashMap<Integer, Node>();

	private int maxXSize;
	private int maxYSize;

	/**
	 * Create a new board
	 * 
	 * @param nodes the nodes that the board will consist of
	 */
	public StandardBoard(List<Node> nodes) {
		Iterator<Node> it = nodes.iterator();
		while (it.hasNext()) {
			Node next = it.next();
			if (next.getXCoordinate() > maxXSize) {
				maxXSize = next.getXCoordinate();
			}
			if (next.getYCoordinate() > maxYSize) {
				maxYSize = next.getYCoordinate();
			}

			/**
			 * Doesn't add duplicates with same x/y-value. If duplicate found
			 * saves node that is marked
			 */
			if (!nodesByXY.containsKey(next.hashCode())) {
				nodesByXY.put(StandardNode.hashCode(next.getXCoordinate(), next.getYCoordinate()), next);
				nodesByID.put(next.getId(), next);
			} else if (!nodesByXY.get(next.hashCode()).isMarked()) {
				nodesByID.remove(nodesByXY.get(next.hashCode()).getId());
				nodesByXY.put(StandardNode.hashCode(next.getXCoordinate(), next.getYCoordinate()), next);
				nodesByID.put(next.getId(), next);
			}
		}
	}

	/**
	 * Find a node by its ID
	 * 
	 * @param nodeId Id for the searched node
	 * @return the node with the specified id
	 */
	public StandardNode getNodeById(String nodeId) {
		return (StandardNode) nodesByID.get(nodeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.board.Board#getNode(int, int)
	 */
	public StandardNode getNode(int x, int y) {
		return (StandardNode) nodesByXY.get(StandardNode.hashCode(x, y));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.board.Board#getNodes()
	 */
	public List<Node> getNodes() {
		ArrayList<Node> nodes = new ArrayList<Node>(nodesByID.values());
		Collections.sort(nodes, new NodeComparator());
		return nodes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String player;
		int lastRow = 0;
		List<Node> orderedNodes = getNodes();
		PlayerColour playerColors = PlayerColour.createPlayerColour();
		int lastYindex = -1;

		for (Node next : orderedNodes) {
			if (lastRow != next.getXCoordinate()) {
				sb.append("\n");
				lastRow = next.getXCoordinate();
				lastYindex = -1;
			}
			sb.append(addSpacesWhereNeeded(next.getYCoordinate(), lastYindex));
			lastYindex = next.getYCoordinate();
			player = next.getOccupantPlayerId();
			if (player == null) {
				sb.append(UNOWNED);
			} else {
				sb.append(playerColors.getPlayerColor(player));
			}
		}
		return sb.toString();
	}

	private String addSpacesWhereNeeded(int yCoordinate, int lastYindex) {
		StringBuilder sb = new StringBuilder();
		if (lastYindex == -1) {
			for (int i = 0; i < yCoordinate; i++) {
				sb.append(" ");
			}
		} else if (yCoordinate > (lastYindex + 1)) {
			for (int i = lastYindex; i < yCoordinate; i++) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.board.Board#getMaxX()
	 */
	public int getMaxX() {
		return maxXSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.board.Board#getMaxY()
	 */
	public int getMaxY() {
		return maxYSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.board.Board#hasNode(int, int)
	 */
	public boolean hasNode(int x, int y) {
		return (this.getNode(x, y) != null);
	}
}
