package kth.game.othello;

import game.othello.OthelloCreatorImpl;
import game.othello.board.BoardCreatorImpl;
import game.othello.board.NodeCreatorImpl;
import game.othello.player.PlayerCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;

public class RunGame {
	public static void main(String[] args) {
		RunGame.testComputerGameToTheFinish();
	}

	public static void testComputerGameToTheFinish() {
		OthelloFactory factory = new OthelloFactory(new OthelloCreatorImpl(), new BoardFactory(new NodeCreatorImpl(),
				new BoardCreatorImpl()), new PlayerCreatorImpl());
		Othello othello = factory.createComputerGameOnClassicalBoard();

		othello.start();
		while (othello.isActive()) {
			othello.move();
		}
	}
}
