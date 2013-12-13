package kth.game.othello;

import game.othello.OthelloCreatorImpl;
import game.othello.ai.BottomRightAIStrategy;
import game.othello.ai.NaiveAIStrategy;
import game.othello.ai.TopLeftAIStrategy;
import game.othello.board.BoardCreatorImpl;
import game.othello.board.NodeCreatorImpl;
import game.othello.player.PlayerCreatorImpl;

import java.util.ArrayList;

import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;

import org.junit.Test;

/**
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 * 1. Create three computers.
 * 
 * 2. Use the diamond board from the BoardFactory.
 * 
 * 3. Play the game and show the result.
 * 
 */
public class demo6 {
	@Test
	public void testDemo6() {
		Othello othello = setUpGame();

		othello.start();

		System.out.println(othello);
		while (othello.isActive()) {
			System.out.println(othello);
			othello.move();
		}
		System.out.println(othello);
	}

	private Othello setUpGame() {
		OthelloFactory factory = new OthelloFactory(new OthelloCreatorImpl(), null, null);

		PlayerCreatorImpl playerCreator = new PlayerCreatorImpl();
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(playerCreator.createComputerPlayer("Computer1", new NaiveAIStrategy()));
		players.add(playerCreator.createComputerPlayer("Computer2", new BottomRightAIStrategy()));
		players.add(playerCreator.createComputerPlayer("Computer3", new TopLeftAIStrategy()));

		Othello othello = factory.createGame(
				new BoardFactory(new NodeCreatorImpl(), new BoardCreatorImpl()).getDiamondBoard(players, 7), players);

		return othello;
	}
}
