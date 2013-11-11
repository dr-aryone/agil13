package kth.game.othello.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import kth.game.othello.player.Player;

import org.junit.Test;

public class OthelloTest extends TestCase {

	private Player createMockedPlayer(int playerIndex) {
		Player player = mock(Player.class);
		when(player.getId()).thenReturn("player" + playerIndex);
		when(player.getName()).thenReturn("Player " + playerIndex);
		return player;
	}

	@Test
	public void testInitialPlayer() {
		Player p1 = createMockedPlayer(1);
		Player p2 = createMockedPlayer(2);

	}
}
