package kth.game.othello.player.movestrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

public class RandomMoveStrategy implements MoveStrategy {

	@Override
	public String getName() {
		return "Random move strategy";
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		List<Node> possibleMoves = getPossibleMoves(board.getNodes(), playerId, rules);
		if (possibleMoves.isEmpty())
			return null;
		Collections.shuffle(possibleMoves);
		return possibleMoves.get(0);
	}

	private List<Node> getPossibleMoves(List<Node> nodes, String playerId, Rules rules) {
		List<Node> possibleMoves = new ArrayList<>();
		for (Node node : nodes) {
			if (rules.isMoveValid(playerId, node.getId())) {
				possibleMoves.add(node);
			}
		}
		return possibleMoves;
	}
}
