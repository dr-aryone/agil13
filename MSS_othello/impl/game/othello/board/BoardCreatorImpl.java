package game.othello.board;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.Node;

/**
 * The responsibility of this class is to create boards
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class BoardCreatorImpl implements BoardCreator {

	@Override
	public Board createBoard(List<Node> nodes) {
		return new StandardBoard(nodes);
	}
}
