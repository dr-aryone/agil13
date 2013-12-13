package kth.game.othello.board.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCreator;
import kth.game.othello.player.Player;

public class Diamond {
	private BoardCreator boardCreator;

	private NodeCreator nodeCreator;

	public Diamond(NodeCreator nodeCreator, BoardCreator boardCreator) {
		this.nodeCreator = nodeCreator;
		this.boardCreator = boardCreator;
	}

	private void addMarkedNodes(Set<Node> nodes, List<Player> players, int size) {
		String player1Id = players.get(0).getId();
		String player2Id = players.get(1).getId();
		String player3Id = players.get(2).getId();

		// middle index for the node coordinates
		int middleIndex = (size - 1) / 2;

		nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(middleIndex - 1, middleIndex - 1, player1Id));
		nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(middleIndex - 1, middleIndex + 0, player2Id));
		nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(middleIndex - 1, middleIndex + 1, player3Id));

		nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(middleIndex - 0, middleIndex - 1, player2Id));
		nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(middleIndex - 0, middleIndex + 0, player3Id));
		nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(middleIndex - 0, middleIndex + 1, player1Id));

		nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(middleIndex + 1, middleIndex - 1, player3Id));
		nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(middleIndex + 1, middleIndex + 0, player1Id));
		nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(middleIndex + 1, middleIndex + 1, player2Id));
	}

	/**
	 * This board has a shape of a diamond and can be played by three players.
	 * 
	 * @param players The list of players, that must be three
	 * @param size an odd number being the size of the board
	 * @return the diamond board
	 */
	public Board getDiamondBoard(List<Player> players, int size) {
		if ((size % 2) == 0) {
			throw new IllegalArgumentException("The size must be an odd number.");
		}
		if (players.size() != 3) {
			throw new IllegalArgumentException("The number of players must be three.");
		}

		Set<Node> nodes = new HashSet<Node>();
		addMarkedNodes(nodes, players, size);

		int maxIndex = size - 1;
		int middleIndex = maxIndex / 2;

		// Upper part
		for (int yIndex = 0; yIndex < middleIndex; yIndex++) {
			for (int xIndex = middleIndex - yIndex; xIndex <= (middleIndex + yIndex); xIndex++) {
				nodes.add(nodeCreator.createNodeWithCoordinate(xIndex, yIndex));
			}
		}

		// Lower part
		for (int yIndex = middleIndex; yIndex <= maxIndex; yIndex++) {
			for (int xIndex = middleIndex - (maxIndex - yIndex); xIndex <= (middleIndex + (maxIndex - yIndex)); xIndex++) {
				nodes.add(nodeCreator.createNodeWithCoordinate(xIndex, yIndex));
			}
		}

		return boardCreator.createBoard(new ArrayList<Node>(nodes));
	}
}
