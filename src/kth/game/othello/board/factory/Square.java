package kth.game.othello.board.factory;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCreator;
import kth.game.othello.player.Player;

public class Square {
	private BoardCreator boardCreator;

	private NodeCreator nodeCreator;

	public Square(NodeCreator nodeCreator, BoardCreator boardCreator) {
		this.nodeCreator = nodeCreator;
		this.boardCreator = boardCreator;
	}

	/**
	 * This board is the traditional board for two players.
	 * 
	 * @param size an even number determining the size of the board
	 * @param players the list players of players, that must be two
	 * @return
	 */
	public Board getQuadraticBoard(int size, List<Player> players) {
		String player1Id = players.get(0).getId();
		String player2Id = players.get(1).getId();

		List<Node> nodes = new ArrayList<Node>();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (((x == (size / 2)) && (y == (size / 2))) || ((x == ((size / 2) - 1)) && (y == ((size / 2) - 1)))) {
					nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(x, y, player1Id));
				} else if (((x == (size / 2)) && (y == ((size / 2) - 1)))
						|| ((x == ((size / 2) - 1)) && (y == (size / 2)))) {
					nodes.add(nodeCreator.createNodeWithCoordinateAndOccupant(x, y, player2Id));
				} else {
					nodes.add(nodeCreator.createNodeWithCoordinate(x, y));
				}
			}
		}
		return boardCreator.createBoard(nodes);
	}

}