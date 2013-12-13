package game.othello.ai;

import java.util.List;
import java.util.Random;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * An AI of poor intelligence that can choose a move on a board of Othello
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class NaiveAIStrategy implements MoveStrategy {

	private final String name = "Naive AI strategy";

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.player.movestrategy.MoveStrategy#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * kth.game.othello.player.movestrategy.MoveStrategy#move(java.lang.String,
	 * kth.game.othello.Rules, kth.game.othello.board.Board)
	 */
	public Node move(String playerId, Rules rules, Board board) {
		List<Node> nodes = board.getNodes();
		Random r = new Random();
		Node node = nodes.get(r.nextInt(nodes.size()));
		while (!rules.isMoveValid(playerId, node.getId())) {
			node = nodes.get(r.nextInt(nodes.size()));
		}
		return node;
	}
}
