package agil13;

import static org.junit.Assert.assertEquals;
import kth.game.othello.BasicOthelloCreator;
import kth.game.othello.Othello;
import kth.game.othello.OthelloCreator;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.BasicBoardCreator;
import kth.game.othello.board.BasicNodeCreator;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.BasicPlayerCreator;
import kth.game.othello.player.PlayerCreator;

public class DebugScore {

	public static void main(String[] args) {
		new DebugScore().debug();
	}

	private void debug() {
		Othello othello = getOthelloFactory().createHumanVersusComputerGameOnOriginalBoard();
		String playerId = othello.getPlayers().get(0).getId();
		othello.start(playerId);
		othello.move(playerId, othello.getBoard().getNode(5, 3).getId());
		assertEquals(4, othello.getScore().getPoints(playerId));
	}

	private OthelloFactory getOthelloFactory() {
		OthelloCreator othelloCreator = new BasicOthelloCreator();
		BoardFactory boardFactory = getBoardFactory();
		PlayerCreator playerCreator = getPlayerCreator();
		return new OthelloFactory(othelloCreator, boardFactory, playerCreator);
	}

	private PlayerCreator getPlayerCreator() {
		return new BasicPlayerCreator();
	}

	private BoardFactory getBoardFactory() {
		return new BoardFactory(new BasicNodeCreator(), new BasicBoardCreator());
	}

}
