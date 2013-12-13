package game.othello.ai;

import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * Main class for handling an Othello game, making moves on the board and
 * handling player turns
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class TopLeftAIStrategy implements MoveStrategy {

	private final String name = "Top left strategy";

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		List<Node> nodes = board.getNodes();
		for (Node n : nodes) {
			if (rules.isMoveValid(playerId, n.getId())) {
				return n;
			}
		}
		return null; // should not happen but don't know what else to do
	}
}
