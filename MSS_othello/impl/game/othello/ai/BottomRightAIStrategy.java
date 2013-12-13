package game.othello.ai;

import java.util.Collections;
import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class BottomRightAIStrategy implements MoveStrategy {

	private final String name = "Bottom right strategy";

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		List<Node> nodes = board.getNodes();
		Collections.reverse(nodes);
		for (Node n : nodes) {
			if (rules.isMoveValid(playerId, n.getId())) {
				return n;
			}
		}
		return null; // Should not get here but must have something anyway
	}

}
