package tournament.tournamentorder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import kth.game.othello.player.Player;
import match.StandardMatch;

import org.junit.Test;

import tournamentorder.StandardTournamentOrder;
import tournamentorder.TournamentOrder;

public class StandardTournamentOrderTest {

	@Test
	public void testGetMatches() {
		StandardMatch m;
		Player player1 = mock(Player.class);
		when(player1.getId()).thenReturn("1");
		Player player2 = mock(Player.class);
		when(player2.getId()).thenReturn("2");
		Player player3 = mock(Player.class);
		when(player3.getId()).thenReturn("3");
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);

		TournamentOrder order = new StandardTournamentOrder(players);
		m = order.getNextMatch();
		assertEquals("1", m.getFirstPlayer().getId());
		assertEquals("2", m.getPlayers().get(1).getId());

		m = order.getNextMatch();
		assertEquals("1", m.getFirstPlayer().getId());
		assertEquals("3", m.getPlayers().get(1).getId());

		m = order.getNextMatch();
		assertEquals("2", m.getFirstPlayer().getId());
		assertEquals("1", m.getPlayers().get(1).getId());

		m = order.getNextMatch();
		assertEquals("2", m.getFirstPlayer().getId());
		assertEquals("3", m.getPlayers().get(1).getId());

		m = order.getNextMatch();
		assertEquals("3", m.getFirstPlayer().getId());
		assertEquals("1", m.getPlayers().get(1).getId());

		m = order.getNextMatch();
		assertEquals("3", m.getFirstPlayer().getId());
		assertEquals("2", m.getPlayers().get(1).getId());
		assert (true);
	}
}
