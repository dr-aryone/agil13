package kth.game.othello.player.movestrategy;

import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * An implementation of the {@link MoveStrategy} interface that will perform
 * moves based on a greedy algorithm strategy. In other words, this will suggest
 * the move that ends up in the most swapped bricks that turn.
 */
public class GreedyMoveStrategy implements MoveStrategy {

	@Override
	public String getName() {
		return "Greedy move strategy";
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		int maxFlips = 0;
		Node maxFlipsArg = null;
		for (Node node : board.getNodes()) {
			if (!rules.isMoveValid(playerId, node.getId()))
				continue;
			List<Node> currentMove = rules.getNodesToSwap(playerId, node.getId());
			if (currentMove.size() > maxFlips) {
				maxFlipsArg = node;
				maxFlips = currentMove.size();
			}
		}
		return maxFlipsArg;
	}

}
