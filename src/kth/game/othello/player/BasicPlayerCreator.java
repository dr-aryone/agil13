package kth.game.othello.player;

import kth.game.othello.player.movestrategy.GreedyMoveStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;

public class BasicPlayerCreator implements PlayerCreator {

	private static int nextId = 1;

	@Override
	public Player createComputerPlayer(String name) {
		return createComputerPlayer(name, new GreedyMoveStrategy());
	}

	@Override
	public Player createComputerPlayer(String name, MoveStrategy moveStrategy) {
		return new ComputerPlayer(getNextId(), name, moveStrategy);
	}

	@Override
	public Player createHumanPlayer(String name) {
		return new HumanPlayer(getNextId(), name);
	}

	private static String getNextId() {
		return String.valueOf(nextId++);
	}
}
