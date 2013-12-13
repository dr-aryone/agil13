package test.tournament;

import org.junit.Test;

import tournament.Tournament;

public class TournamentTest {
	@Test(expected = IllegalStateException.class)
	public void noDisplayPatternUsedThrowsException() {
		Tournament tournament = new Tournament(null);
		tournament.runGame(null);
	}
}
