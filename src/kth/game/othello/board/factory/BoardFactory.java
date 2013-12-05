package kth.game.othello.board.factory;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.NodeCreator;
import kth.game.othello.player.Player;

public class BoardFactory {
	private BoardCreator boardCreator;

	private NodeCreator nodeCreator;

	public BoardFactory(NodeCreator nodeCreator, BoardCreator boardCreator) {
		this.nodeCreator = nodeCreator;
		this.boardCreator = boardCreator;
	}

	/**
	 * This board has a shape of a castle and can be played by two players.
	 * 
	 * @param players The list of players, that must be three
	 * @return the board
	 */
	public Board getCastleBoard(List<Player> players) {
		Castle castle = new Castle(nodeCreator, boardCreator);
		return castle.getBoard(players);
	}

	/**
	 * This board has a shape of a diamond and can be played by three players.
	 * 
	 * @param players The list of players, that must be three
	 * @param size an odd number being the size of the board
	 * @return the diamond board
	 */
	public Board getDiamondBoard(List<Player> players, int size) {
		Diamond diamond = new Diamond(nodeCreator, boardCreator);
		return diamond.getDiamondBoard(players, size);
	}

	/**
	 * This board is the traditional board for two players.
	 * 
	 * @param size an even number determining the size of the board
	 * @param players the list players of players, that must be two
	 * @return
	 */
	public Board getQuadraticBoard(int size, List<Player> players) {
		Square square = new Square(nodeCreator, boardCreator);
		return square.getQuadraticBoard(size, players);
	}

}
