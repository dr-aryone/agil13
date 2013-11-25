package kth.game.othello;

import java.util.List;

import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;

public class PlayerHandlerTest extends MockingBase {

	@Test
	public void testRandomPlayer() {
		List<Player> players = createMockedPlayers(10);
		PlayerHandler playerHandler = new PlayerHandler(players);
		Assert.assertTrue("0123456789".contains(playerHandler.randomPlayer().getId()));
		Assert.assertTrue("0123456789".contains(playerHandler.randomPlayer().getId()));
		Assert.assertTrue("0123456789".contains(playerHandler.randomPlayer().getId()));
		Assert.assertTrue("0123456789".contains(playerHandler.randomPlayer().getId()));
		Assert.assertTrue("0123456789".contains(playerHandler.randomPlayer().getId()));
		Assert.assertTrue("0123456789".contains(playerHandler.randomPlayer().getId()));
	}

	@Test
	public void testChangePlayer() {
		List<Player> players = createMockedPlayers(5);
		PlayerHandler playerHandler = new PlayerHandler(players);

		Assert.assertNull(playerHandler.getPlayerInTurn());
		Assert.assertEquals("0", playerHandler.getNextPlayer().getId());
		playerHandler.changePlayer();

		Assert.assertEquals("0", playerHandler.getPlayerInTurn().getId());
		Assert.assertEquals("1", playerHandler.getNextPlayer().getId());
		playerHandler.changePlayer();

		Assert.assertEquals("1", playerHandler.getPlayerInTurn().getId());
		Assert.assertEquals("2", playerHandler.getNextPlayer().getId());
		playerHandler.changePlayer();

		Assert.assertEquals("2", playerHandler.getPlayerInTurn().getId());
		Assert.assertEquals("3", playerHandler.getNextPlayer().getId());
		playerHandler.changePlayer();

		Assert.assertEquals("3", playerHandler.getPlayerInTurn().getId());
		Assert.assertEquals("4", playerHandler.getNextPlayer().getId());
		playerHandler.changePlayer();

		Assert.assertEquals("4", playerHandler.getPlayerInTurn().getId());
		Assert.assertEquals("0", playerHandler.getNextPlayer().getId());
		playerHandler.changePlayer();
	}

	@Test
	public void testGetPlayer() {
		List<Player> players = createMockedPlayers(5);
		PlayerHandler playerHandler = new PlayerHandler(players);
		Assert.assertEquals("0", playerHandler.getPlayer("0").getId());
		Assert.assertEquals("1", playerHandler.getPlayer("1").getId());
		Assert.assertEquals("2", playerHandler.getPlayer("2").getId());
		Assert.assertEquals("3", playerHandler.getPlayer("3").getId());
		Assert.assertEquals("4", playerHandler.getPlayer("4").getId());
		Assert.assertNull(playerHandler.getPlayer("5"));
		Assert.assertNull(playerHandler.getPlayer("-1"));
		Assert.assertNull(playerHandler.getPlayer("1283723"));
	}
}
