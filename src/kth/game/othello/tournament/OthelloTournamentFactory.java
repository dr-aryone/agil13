package kth.game.othello.tournament;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kth.game.othello.BasicOthelloCreator;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.BasicBoardCreator;
import kth.game.othello.board.BasicNodeCreator;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.BasicPlayerCreator;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.tournament.resultdisplay.ResultDisplay;

/**
 * This entity is responsible for creating tournaments.
 */
public class OthelloTournamentFactory {

	public static OthelloTournament createTournamentFromPlayers(List<Player> players, ResultDisplay resultDisplay,
			OthelloFactory othelloFactory, Schedule schedule, BoardFactory boardFactory) {
		for (Player player : players) {
			for (Player opponent : players) {
				if (!player.equals(opponent)) {
					schedule.addMatchUp(new MatchUp(player, othelloFactory.createGame(
							boardFactory.getQuadraticBoard(8, Arrays.asList(player, opponent)), players)));
				}
			}
		}
		return new OthelloTournament(resultDisplay, schedule);
	}

	public static OthelloTournament createTournamentFromMoveStrategies(List<MoveStrategy> strategies,
			ResultDisplay resultDisplay) {
		BoardFactory boardFactory = new BoardFactory(new BasicNodeCreator(), new BasicBoardCreator());
		OthelloFactory factory = new OthelloFactory(new BasicOthelloCreator(), boardFactory, new BasicPlayerCreator());
		return createTournamentFromMoveStrategies(strategies, resultDisplay, new BasicPlayerCreator(), boardFactory,
				factory);
	}

	static OthelloTournament createTournamentFromMoveStrategies(List<MoveStrategy> strategies,
			ResultDisplay resultDisplay, PlayerCreator playerCreator, BoardFactory boardFactory,
			OthelloFactory othelloFactory) {
		List<Player> players = new ArrayList<>();
		for (int i = 0; i < strategies.size(); i++) {
			MoveStrategy moveStrategy = strategies.get(i);
			String playerName = String.format("Player %d", i);
			players.add(playerCreator.createComputerPlayer(playerName, moveStrategy));
		}
		return createTournamentFromPlayers(players, resultDisplay, othelloFactory, new Schedule(), boardFactory);
	}
}
