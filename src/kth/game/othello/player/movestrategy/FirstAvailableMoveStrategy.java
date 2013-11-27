package kth.game.othello.player.movestrategy;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * This is an implementation of the {@link MoveStrategy} interface that will
 * suggest a move based on the first valid one it can find, when searching
 * through the existing nodes in order.
 */
public class FirstAvailableMoveStrategy implements MoveStrategy {

	@Override
	public String getName() {
		return "First Available Move Strategy";
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		for (Node node : board.getNodes()) {
			if (rules.isMoveValid(playerId, node.getId())) {
				return node;
			}
		}
		return null;
	}

}
