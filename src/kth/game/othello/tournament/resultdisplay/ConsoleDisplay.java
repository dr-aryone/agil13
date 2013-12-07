package kth.game.othello.tournament.resultdisplay;

import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.tournament.Results;

public class ConsoleDisplay implements ResultDisplay {

	@Override
	public void displayResults(List<Player> players, Results results) {
		System.out.println("======SCORE=====");
		for (Player player : players) {
			System.out.println(player.getName() + ": " + results.getPointsForPlayer(player));
		}
	}

}
