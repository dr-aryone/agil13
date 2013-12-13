package tournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import kth.game.othello.Othello;
import match.StandardMatch;
import tournamentorder.TournamentOrder;
import DisplayStrategies.RunPattern;

/**
 * The responsibility of this class is to run a tournament
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class Tournament {

	private RunPattern displayStrategy;
	private TournamentOrder tournamentOrder;

	private List<StandardMatch> results;

	/**
	 * Create a new tournament
	 * 
	 * @param tournamentOrder the setting for the tournament
	 */
	public Tournament(TournamentOrder tournamentOrder) {
		this.tournamentOrder = tournamentOrder;
		this.results = new ArrayList<StandardMatch>();
	}

	/**
	 * Play the tournament
	 */
	public void play() {
		StandardMatch match;
		while ((match = tournamentOrder.getNextMatch()) != null) {
			this.runGame(match.getGame());
			this.results.add(match);
		}
	}

	/**
	 * Get the tournament results
	 * 
	 * @return the ranking of the participants of this tournament
	 */
	public String getTournamentResults() {
		HashMap<String, Result> hs = new HashMap<String, Result>();
		for (StandardMatch match : results) {
			String player1 = match.getGame().getPlayers().get(0).getId();
			String player2 = match.getGame().getPlayers().get(1).getId();
			if (hs.get(player1) == null) {
				hs.put(player1, new Result(match.getGame().getPlayers().get(0).getName()));
			}
			if (hs.get(player2) == null) {
				hs.put(player2, new Result(match.getGame().getPlayers().get(1).getName()));
			}
			hs.get(match.getWinner().getId()).wins++;
			hs.get(player1).nodes += match.getGame().getScore().getPoints(player1);
			hs.get(player2).nodes += match.getGame().getScore().getPoints(player2);
		}

		ArrayList<Result> sortedResults = new ArrayList<Result>(hs.values());
		Collections.sort(sortedResults);

		StringBuilder sb = new StringBuilder();
		sb.append("Wins\tNodes\tStrategy\n");
		for (Result result : sortedResults) {
			sb.append(result.wins + "\t" + result.nodes + "\t" + result.playerName + "\n");
		}

		return sb.toString();
	}

	/**
	 * Set a dipslaystrategy
	 * 
	 * @param displayStrategy the displaystrategy when playing the tournament
	 */
	public void setRunStrategy(RunPattern displayStrategy) {
		this.displayStrategy = displayStrategy;
	}

	/**
	 * Run a game of othello
	 * 
	 * @param othello the specific othello game to run
	 * @throws IllegalStateException if no runStrategy has been set
	 */
	public void runGame(Othello othello) throws IllegalStateException {
		if (this.displayStrategy == null) {
			throw new IllegalStateException(
					"No method for running the game has been set. Set it by setRunStrategy(...)");
		}
		displayStrategy.runGame(othello);
	}

	/**
	 * Holds the results for a given player
	 */
	private class Result implements Comparable<Result> {
		public String playerName;
		public int wins = 0;
		public int nodes = 0;

		public Result(String playerName) {
			this.playerName = playerName;
		}

		@Override
		public int compareTo(Result r) {
			if ((r.wins - this.wins) != 0) {
				return r.wins - this.wins;
			}
			return r.nodes - this.nodes;
		}
	}
}
