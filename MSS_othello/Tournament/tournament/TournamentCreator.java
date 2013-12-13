package tournament;

import tournamentorder.TournamentOrder;
import DisplayStrategies.RunPattern;

/**
 * The responsibility of this class is to create tournaments
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class TournamentCreator {
	/**
	 * Create a new tournament
	 * 
	 * @param runPattern the pattern used to display the tournament matches
	 * @param tournamentOrder the settings for the tournament
	 * @return the newly created tournament
	 */
	public Tournament createTournament(RunPattern runPattern, TournamentOrder tournamentOrder) {
		Tournament tournament = new Tournament(tournamentOrder);
		tournament.setRunStrategy(runPattern);
		return tournament;
	}
}
