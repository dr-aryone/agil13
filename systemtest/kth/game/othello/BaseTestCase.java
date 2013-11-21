package kth.game.othello;

import java.util.Collections;
import java.util.List;

import kth.game.othello.board.BasicBoardCreator;
import kth.game.othello.board.BasicNodeCreator;
import kth.game.othello.board.Node;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.BasicPlayerCreator;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.movestrategy.GreedyMoveStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;

public abstract class BaseTestCase {

	protected int getNumberOfOccupiedNodes(Othello othello) {
		int occupiedNodesCounter = 0;
		for (Node node : othello.getBoard().getNodes()) {
			if (node.isMarked()) {
				occupiedNodesCounter++;
			}
		}
		return occupiedNodesCounter;
	}

	protected BoardFactory getBoardFactory() {
		return new BoardFactory(new BasicNodeCreator(), new BasicBoardCreator());
	}

	protected MoveStrategy getNewMoveStrategy() {
		return new GreedyMoveStrategy();
	}

	protected PlayerCreator getPlayerCreator() {
		return new BasicPlayerCreator();
	}

	protected OthelloFactory getOthelloFactory() {
		OthelloCreator othelloCreator = new BasicOthelloCreator();
		BoardFactory boardFactory = getBoardFactory();
		PlayerCreator playerCreator = getPlayerCreator();
		return new OthelloFactory(othelloCreator, boardFactory, playerCreator);
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
