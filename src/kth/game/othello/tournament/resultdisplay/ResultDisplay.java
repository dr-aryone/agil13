package kth.game.othello.tournament.resultdisplay;

import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.tournament.Results;

public interface ResultDisplay {

	// defines in what way we can display the results of an othello tournament

	void displayResults(List<Player> players, Results results);
}
