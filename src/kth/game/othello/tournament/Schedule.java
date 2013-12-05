package kth.game.othello.tournament;

import java.util.Deque;
import java.util.LinkedList;

class Schedule {

	private final Deque<MatchUp> matchUps = new LinkedList<>();

	void addMatchUp(MatchUp matchUp) {
		matchUps.add(matchUp);
	}

	MatchUp nextMatchUp() {
		return matchUps.poll();
	}

	boolean isOver() {
		return matchUps.isEmpty();
	}
	// contains a a list of match-ups and their order

	// hopefully some other pretty functionality too...
}
