package kth.game.othello.test;

import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;
import kth.game.othello.BasicOthelloFactory;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

public abstract class BaseTestCase extends TestCase {

	protected Object getNumberOfOccupiedNodes(Othello othello) {
		int occupiedNodesCounter = 0;
		for (Node node : othello.getBoard().getNodes()) {
			if (node.isMarked()) {
				occupiedNodesCounter++;
			}
		}
		return occupiedNodesCounter;
	}

	protected OthelloFactory getOthelloFactory() {
		return new BasicOthelloFactory();
	}

	protected List<Node> makeAHumanMove(Othello othello, Player human) {
		List<Node> possibleNodes = othello.getBoard().getNodes();
		Collections.shuffle(possibleNodes);
		for (Node node : possibleNodes) {
			if (othello.isMoveValid(human.getId(), node.getId())) {
				return othello.move(human.getId(), node.getId());
			}
		}
		throw new IllegalStateException();
	}
}
