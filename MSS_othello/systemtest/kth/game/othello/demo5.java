package kth.game.othello;

import game.othello.OthelloCreatorImpl;
import game.othello.board.BoardCreatorImpl;
import game.othello.board.NodeCreatorImpl;
import game.othello.player.PlayerCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 *         1. Start a human against human game.
 * 
 *         2. Make four moves for each player.
 * 
 *         3. Show the score.
 * 
 */
public class demo5 {
	@Test
	public void testDemo5() {
		Othello othello = setUpGame();

		Player player1 = othello.getPlayers().get(0);
		Player player2 = othello.getPlayers().get(1);

		othello.start(player1.getId());
		Assert.assertEquals(player1.getId(), othello.getPlayerInTurn().getId());

		String nodeId;
		// make 8 moves here
		nodeId = othello.getBoard().getNode(3, 5).getId();
		othello.move(player1.getId(), nodeId);
		System.out.println(othello);

		nodeId = othello.getBoard().getNode(2, 3).getId();
		othello.move(player2.getId(), nodeId);
		System.out.println(othello);

		// Show the score here
		System.out.println("The score after 4 moves each is:");
		System.out.println(othello.getScore());
	}

	private Othello setUpGame() {
		OthelloFactory factory = new OthelloFactory(new OthelloCreatorImpl(), new BoardFactory(new NodeCreatorImpl(),
				new BoardCreatorImpl()), new PlayerCreatorImpl());
		Othello othello = factory.createHumanGameOnOriginalBoard();
		return othello;
	}

}
