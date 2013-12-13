package tournamentorder;

import match.StandardMatch;

/**
 * Responsibility of this interface is too generate new matches in a given
 * order. Order is dependant on implementation
 * 
 * @author jonac
 * 
 */
public interface TournamentOrder {

	/**
	 * Returns the next match in order
	 * 
	 * @return next match, if no more matches exist returns null
	 */
	public abstract StandardMatch getNextMatch();

}