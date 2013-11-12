package kth.game.othello.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import junit.framework.TestCase;
import kth.game.othello.BasicOthelloFactory;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.player.Player;

import org.junit.Test;

public class OthelloTest extends TestCase {

	static final OthelloFactory othelloFactory = new BasicOthelloFactory();

	private Player createMockedPlayer(int playerIndex) {
		Player player = mock(Player.class);
		when(player.getId()).thenReturn("player" + playerIndex);
		when(player.getName()).thenReturn("Player " + playerIndex);
		return player;
	}

	@Test
	public void testInitialPlayer() {
		Othello humanVersusComputer = othelloFactory.createHumanVersusComputerGameOnOriginalBoard();
		List<Player> players = humanVersusComputer.getPlayers();
		assertEquals(Player.Type.COMPUTER, players.get(0).getType());
		assertEquals(Player.Type.HUMAN, players.get(1).getType());
	}
}
