package kth.game.othello.tournament;

import java.util.Arrays;
import java.util.List;

import kth.game.othello.BaseTestCase;
import kth.game.othello.player.movestrategy.FirstAvailableMoveStrategy;
import kth.game.othello.player.movestrategy.GreedyMoveStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;
import kth.game.othello.tournament.resultdisplay.ConsoleDisplay;

import org.junit.Test;

public class OthelloTournamentTest extends BaseTestCase {

	@Test
	public void testTournamentWithView() {
		List<MoveStrategy> strategies = Arrays.asList(new GreedyMoveStrategy(), new RandomMoveStrategy(),
				new FirstAvailableMoveStrategy());

		OthelloTournamentFactory.createTournamentFromMoveStrategies(strategies, new ConsoleDisplay())
				.displayGraphical().playTournament();
	}

	@Test
	public void testTournamentWithoutView() {
		List<MoveStrategy> strategies = Arrays.asList(new GreedyMoveStrategy(), new RandomMoveStrategy(),
				new FirstAvailableMoveStrategy());
		OthelloTournamentFactory.createTournamentFromMoveStrategies(strategies, new ConsoleDisplay()).playTournament();
	}

}
