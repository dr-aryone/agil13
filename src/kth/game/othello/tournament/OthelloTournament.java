package kth.game.othello.tournament;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.player.BasicPlayerCreator;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.movestrategy.MoveStrategy;

public class OthelloTournament {

	private final ResultDisplay resultDisplay;
	private final Schedule schedule;

	OthelloTournament(ResultDisplay resultDisplay) {
		this(resultDisplay, new Schedule());
	}

	OthelloTournament(ResultDisplay resultDisplay, Schedule schedule) {
		this.resultDisplay = resultDisplay;
		this.schedule = schedule;
	}

	public static OthelloTournament createTournamentFromPlayers(List<Player> players, ResultDisplay resultDisplay) {
		// TODO
		return null;
	}

	public static OthelloTournament createTournamentFromMoveStrategies(List<MoveStrategy> strategies,
			ResultDisplay resultDisplay) {
		List<Player> players = new ArrayList<>();
		PlayerCreator playerCreator = new BasicPlayerCreator();
		for (int i = 0; i < strategies.size(); i++) {
			MoveStrategy moveStrategy = strategies.get(i);
			String playerName = String.format("Player %d", i);
			players.add(playerCreator.createComputerPlayer(playerName, moveStrategy));
		}
		return createTournamentFromPlayers(players, resultDisplay);
	}
}
