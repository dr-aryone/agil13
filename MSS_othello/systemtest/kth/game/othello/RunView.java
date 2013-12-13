package kth.game.othello;

import game.othello.OthelloCreatorImpl;
import game.othello.board.BoardCreatorImpl;
import game.othello.board.NodeCreatorImpl;
import game.othello.player.PlayerCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

public class RunView {
	public static void main(String[] args) {
		OthelloFactory factory = new OthelloFactory(new OthelloCreatorImpl(), new BoardFactory(new NodeCreatorImpl(),
				new BoardCreatorImpl()), new PlayerCreatorImpl());
		Othello othello = factory.createComputerGameOnClassicalBoard();
		OthelloView othelloView = OthelloViewFactory.create(othello, 100, 100);
		othelloView.start();
	}
}
