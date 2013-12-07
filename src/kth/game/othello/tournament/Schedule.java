package kth.game.othello.tournament;

import java.util.Deque;
import java.util.LinkedList;

/**
 * This entity is responsible for Scheduling the MatchUps in a tournament.
 */
class Schedule {

	private final Deque<MatchUp> matchUps = new LinkedList<>();

	/**
	 * Adds a MatchUp to the end of the schedule.
	 * 
	 * @param matchUp
	 *            the MatchUp to be added to the schedule.
	 */
	void addMatchUp(MatchUp matchUp) {
		matchUps.add(matchUp);
	}

	/**
	 * Removes the next MatchUp in the schedule and returns it.
	 * 
	 * @return the next MatchUp in the schedule, or null if the schedule is
	 *         empty.
	 */
	MatchUp nextMatchUp() {
		return matchUps.poll();
	}

	/**
	 * Returns true if there are no more MatchUps in the schedule.
	 * 
	 * @return true if there are no more MatchUps in the schedule, false
	 *         otherwise.
	 */
	boolean isOver() {
		return matchUps.isEmpty();
	}
}
