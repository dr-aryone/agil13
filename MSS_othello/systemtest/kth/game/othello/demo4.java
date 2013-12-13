package kth.game.othello;

import game.othello.OthelloCreatorImpl;
import game.othello.OthelloTheGame;
import game.othello.ai.BottomRightAIStrategy;
import game.othello.ai.NaiveAIStrategy;
import game.othello.ai.TopLeftAIStrategy;
import game.othello.board.BoardCreatorImpl;
import game.othello.board.NodeCreatorImpl;
import game.othello.player.PlayerCreatorImpl;

import java.util.ArrayList;

import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.movestrategy.MoveStrategy;

import org.junit.Test;

/**
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 * 1. Start a computer game with two computers.
 * 
 * 2. Make ten moves with each player.
 * 
 * 3. Change the strategy for one of the players.
 * 
 * 4. Go to step 2 until one of the computers won.
 */
public class demo4 {

	private static final boolean DEBUG_ACTUAL_CHANGE = false;
	private ArrayList<MoveStrategy> strategies = new ArrayList<MoveStrategy>();

	@Test
	public void testDemo4() {
		Othello othello = setUpGame();
		othello.start();
		setUpMoveStrategyList();
		int i = 0;
		MoveStrategy currentMoveStrategy;
		while (makeTenMovesPerPlayer(othello)) {
			currentMoveStrategy = strategies.get(i % strategies.size());
			if (DEBUG_ACTUAL_CHANGE)
				System.out.println(currentMoveStrategy.getName());
			othello.getPlayers().get(0).setMoveStrategy(currentMoveStrategy);
			i++;
		}
	}

	private boolean makeTenMovesPerPlayer(Othello othello) {
		for (int i = 0; i < 20; i++) {
			if (!othello.isActive()) {
				return false;
			}
			othello.move();

			System.out.println((OthelloTheGame) othello);
		}
		return true;
	}

	private void setUpMoveStrategyList() {
		strategies.add(new NaiveAIStrategy());
		strategies.add(new TopLeftAIStrategy());
		strategies.add(new BottomRightAIStrategy());
	}

	private Othello setUpGame() {
		OthelloFactory factory = new OthelloFactory(new OthelloCreatorImpl(), new BoardFactory(new NodeCreatorImpl(),
				new BoardCreatorImpl()), new PlayerCreatorImpl());
		Othello othello = factory.createComputerGameOnClassicalBoard();
		return othello;
	}
}
