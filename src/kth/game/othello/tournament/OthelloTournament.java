package kth.game.othello.tournament;

import java.util.ArrayList;
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

	public static OthelloTournament createTournamentFromPlayers(List<Player> players, ResultDisplay resultDisplay,
			OthelloFactory factory, Schedule schedule) {
		for (Player player : players) {
			for (Player opponent : players) {
				if (!player.equals(opponent)) {
					schedule.addMatchUp(createCorrectMatchUp(factory, player, opponent));
				}
			}
		}

		return new OthelloTournament(resultDisplay, schedule);
	}

	/*
	 * Den här är aningen trasig. Exempel:
	 * factory.createComputerGameOnClassicalBoard() skapar ett nytt Othello med
	 * helt nya players.
	 * 
	 * Dessa players kommer vara hela separata från de players man har angivit i
	 * parametrarna.
	 * 
	 * Gissningsvis ska man använda sig av public Othello createGame(Board
	 * board, List<Player> players) i OthelloFactory istället. Då kommer man
	 * behöva ändra så att de här metoderna tar in en BoardFactory också. Jag
	 * väljer att inte ändra alla testfall och göra det nu.
	 */
	protected static MatchUp createCorrectMatchUp(OthelloFactory factory, Player startingPlayer, Player opponent) {
		if (startingPlayer.getType() == Player.Type.COMPUTER) {
			if (opponent.getType() == Player.Type.COMPUTER)
				return new MatchUp(startingPlayer, factory.createComputerGameOnClassicalBoard());
			else
				return new MatchUp(startingPlayer, factory.createHumanVersusComputerGameOnOriginalBoard());
		} else {
			if (opponent.getType() == Player.Type.COMPUTER)
				return new MatchUp(startingPlayer, factory.createHumanVersusComputerGameOnOriginalBoard());
			else
				return new MatchUp(startingPlayer, factory.createHumanGameOnOriginalBoard());
		}
	}

	public static OthelloTournament createTournamentFromMoveStrategies(List<MoveStrategy> strategies,
			ResultDisplay resultDisplay) {
		BoardFactory boardFactory = new BoardFactory(new BasicNodeCreator(), new BasicBoardCreator());
		OthelloFactory factory = new OthelloFactory(new BasicOthelloCreator(), boardFactory, new BasicPlayerCreator());
		return createTournamentFromMoveStrategies(strategies, resultDisplay, new BasicPlayerCreator(), boardFactory,
				factory);
	}

	static OthelloTournament createTournamentFromMoveStrategies(List<MoveStrategy> strategies,
			ResultDisplay resultDisplay, PlayerCreator playerCreator, BoardFactory boardFactory, OthelloFactory factory) {
		List<Player> players = new ArrayList<>();
		for (int i = 0; i < strategies.size(); i++) {
			MoveStrategy moveStrategy = strategies.get(i);
			String playerName = String.format("Player %d", i);
			players.add(playerCreator.createComputerPlayer(playerName, moveStrategy));
		}
		return createTournamentFromPlayers(players, resultDisplay, factory, new Schedule());
	}
}
