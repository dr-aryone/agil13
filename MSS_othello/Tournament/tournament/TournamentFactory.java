package tournament;

import game.othello.ai.BottomRightAIStrategy;
import game.othello.ai.NaiveAIStrategy;
import game.othello.ai.TopLeftAIStrategy;
import game.othello.player.PlayerCreatorImpl;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;
import tournamentorder.StandardTournamentOrder;
import DisplayStrategies.NoView;
import DisplayStrategies.ShowView;

/**
 * Responsibility of this class is too create factorys according to predefined
 * rulesets
 * 
 * @author Filip Gauffin, Jonas Carlsson
 * 
 */
public class TournamentFactory {
	private PlayerCreator playerCreator;
	private TournamentCreator tournamentCreator;

	public TournamentFactory() {
		this.playerCreator = new PlayerCreatorImpl();
		this.tournamentCreator = new TournamentCreator();
	}

	/**
	 * Create a new game with 3 players that will show the matches
	 * 
	 * @return New Tournament instance
	 */
	public Tournament createTournamentWithThreeComputersUsingView(int timeBetweenSwaps, int timeBetweenMoves) {
		return tournamentCreator.createTournament(new ShowView(timeBetweenSwaps, timeBetweenMoves),
				new StandardTournamentOrder(this.createThreeComputerPlayersWithDifferentStrategies()));
	}

	/**
	 * Create a new game with 3 players and does not show the matches
	 * 
	 * @return New Tournament instance
	 */
	public Tournament createTournamentWithThreeComputersNoView() {
		return tournamentCreator.createTournament(new NoView(),
				new StandardTournamentOrder(this.createThreeComputerPlayersWithDifferentStrategies()));
	}

	// Creates a list of three players with different strategies
	private List<Player> createThreeComputerPlayersWithDifferentStrategies() {
		List<Player> players = new ArrayList<Player>();
		Player player1 = playerCreator.createComputerPlayer("Random pick computer", new NaiveAIStrategy());
		Player player2 = playerCreator.createComputerPlayer("BottomRightAIStrategy", new BottomRightAIStrategy());
		Player player3 = playerCreator.createComputerPlayer("TopLeftAIStrategy", new TopLeftAIStrategy());

		players.add(player1);
		players.add(player2);
		players.add(player3);
		return players;
	}
}
