package agil13;

import kth.game.othello.BasicOthelloCreator;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.BasicBoardCreator;
import kth.game.othello.board.BasicNodeCreator;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.BasicPlayerCreator;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

public class ViewGame {

	public static void main(String[] args) {
		Othello othello = new OthelloFactory(new BasicOthelloCreator(), new BoardFactory(new BasicNodeCreator(),
				new BasicBoardCreator()), new BasicPlayerCreator()).createComputerGameOnClassicalBoard();
		OthelloView view = OthelloViewFactory.create(othello, 10, 50);
		view.start();
	}

}
