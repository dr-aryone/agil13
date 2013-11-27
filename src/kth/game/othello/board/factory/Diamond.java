package kth.game.othello.board.factory;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCreator;
import kth.game.othello.player.Player;

class Diamond {

	private final BoardCreator boardCreator;

	private final NodeCreator nodeCreator;

	Diamond(NodeCreator nodeCreator, BoardCreator boardCreator) {
		this.nodeCreator = nodeCreator;
		this.boardCreator = boardCreator;
	}

	private Map<Point, String> getPreOccupiedNodes(List<Player> players, int size) {
		Map<Point, String> preOccupiedNodes = new HashMap<>();
		String player1Id = players.get(0).getId();
		String player2Id = players.get(1).getId();
		String player3Id = players.get(2).getId();

		// middle index for the node coordinates
		int middleIndex = (size - 1) / 2;

		preOccupiedNodes.put(new Point(middleIndex - 1, middleIndex - 1), player1Id);
		preOccupiedNodes.put(new Point(middleIndex - 1, middleIndex + 0), player2Id);
		preOccupiedNodes.put(new Point(middleIndex - 1, middleIndex + 1), player3Id);

		preOccupiedNodes.put(new Point(middleIndex - 0, middleIndex - 1), player2Id);
		preOccupiedNodes.put(new Point(middleIndex - 0, middleIndex + 0), player3Id);
		preOccupiedNodes.put(new Point(middleIndex - 0, middleIndex + 1), player1Id);

		preOccupiedNodes.put(new Point(middleIndex + 1, middleIndex - 1), player3Id);
		preOccupiedNodes.put(new Point(middleIndex + 1, middleIndex + 0), player1Id);
		preOccupiedNodes.put(new Point(middleIndex + 1, middleIndex + 1), player2Id);

		return preOccupiedNodes;
	}

	/**
	 * This board has a shape of a diamond and can be played by three players.
	 * 
	 * @param players
	 *            The list of players, that must be three
	 * @param size
	 *            an odd number being the size of the board
	 * @return the diamond board
	 */
	public Board getDiamondBoard(List<Player> players, int size) {
		if ((size % 2) == 0) {
			throw new IllegalArgumentException("The size must be an odd number.");
		}
		if (players.size() != 3) {
			throw new IllegalArgumentException("The number of players must be three.");
		}

		Map<Point, String> preOccupiedNodes = getPreOccupiedNodes(players, size);

		Set<Node> nodes = new HashSet<Node>();

		int maxIndex = size - 1;
		int middleIndex = maxIndex / 2;

		// Upper part
		for (int yIndex = 0; yIndex < middleIndex; yIndex++) {
			for (int xIndex = middleIndex - yIndex; xIndex <= (middleIndex + yIndex); xIndex++) {
				Point nodePoint = new Point(xIndex, yIndex);
				nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(xIndex, yIndex,
						preOccupiedNodes.get(nodePoint)));
			}
		}

		// Lower part
		for (int yIndex = middleIndex; yIndex <= maxIndex; yIndex++) {
			for (int xIndex = middleIndex - (maxIndex - yIndex); xIndex <= (middleIndex + (maxIndex - yIndex)); xIndex++) {
				Point nodePoint = new Point(xIndex, yIndex);
				nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(xIndex, yIndex,
						preOccupiedNodes.get(nodePoint)));
			}
		}

		return boardCreator.createBoard(new ArrayList<Node>(nodes));
	}
}
