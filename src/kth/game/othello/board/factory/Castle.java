package kth.game.othello.board.factory;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCreator;
import kth.game.othello.player.Player;

public class Castle {

	private BoardCreator boardCreator;

	private NodeCreator nodeCreator;

	public Castle(NodeCreator nodeCreator, BoardCreator boardCreator) {
		this.nodeCreator = nodeCreator;
		this.boardCreator = boardCreator;
	}

	/**
	 * This board has a shape of a castle and can be played by two players.
	 * 
	 * @param players The list of players, that must be three
	 * @return the board
	 */
	public Board getBoard(List<Player> players) {
		if (players.size() != 2) {
			throw new IllegalArgumentException("The number of players must be two.");
		}

		String player1Id = players.get(0).getId();
		String player2Id = players.get(1).getId();

		List<Node> nodes = new ArrayList<Node>();
		// left upper corner
		nodes.add(nodeCreator.createNodeWithCoordinate(0, 0));
		nodes.add(nodeCreator.createNodeWithCoordinate(1, 0));
		nodes.add(nodeCreator.createNodeWithCoordinate(2, 0));
		nodes.add(nodeCreator.createNodeWithCoordinate(0, 1));
		nodes.add(nodeCreator.createNodeWithCoordinate(0, 2));
		// right upper corner
		nodes.add(nodeCreator.createNodeWithCoordinate(5, 0));
		nodes.add(nodeCreator.createNodeWithCoordinate(6, 0));
		nodes.add(nodeCreator.createNodeWithCoordinate(7, 0));
		nodes.add(nodeCreator.createNodeWithCoordinate(7, 1));
		nodes.add(nodeCreator.createNodeWithCoordinate(7, 2));
		// left lower corner
		nodes.add(nodeCreator.createNodeWithCoordinate(0, 7));
		nodes.add(nodeCreator.createNodeWithCoordinate(1, 7));
		nodes.add(nodeCreator.createNodeWithCoordinate(2, 7));
		nodes.add(nodeCreator.createNodeWithCoordinate(0, 6));
		nodes.add(nodeCreator.createNodeWithCoordinate(0, 5));
		// right lower corner
		nodes.add(nodeCreator.createNodeWithCoordinate(7, 7));
		nodes.add(nodeCreator.createNodeWithCoordinate(6, 7));
		nodes.add(nodeCreator.createNodeWithCoordinate(5, 7));
		nodes.add(nodeCreator.createNodeWithCoordinate(7, 6));
		nodes.add(nodeCreator.createNodeWithCoordinate(7, 5));

		for (int x = 1; x < 7; x++) {
			for (int y = 1; y < 7; y++) {
				if (((x == 3) && (y == 3)) || ((x == 4) && (y == 4))) {
					nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(x, y, player1Id));
				} else if (((x == 4) && (y == 3)) || ((x == 3) && (y == 4))) {
					nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(x, y, player2Id));
				} else {
					nodes.add(nodeCreator.createNodeWithCoordinate(x, y));
				}
			}
		}

		return boardCreator.createBoard(nodes);
	}

}
