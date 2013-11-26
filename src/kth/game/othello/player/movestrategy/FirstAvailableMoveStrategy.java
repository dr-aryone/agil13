package kth.game.othello.player.movestrategy;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

class FirstAvailableMoveStrategy implements MoveStrategy {

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
