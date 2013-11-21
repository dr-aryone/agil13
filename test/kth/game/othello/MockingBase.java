package kth.game.othello;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import org.mockito.Mockito;

public class MockingBase {

	protected List<Player> getPlayers(int numberOfPlayers) {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < numberOfPlayers; i++) {
			Player player = Mockito.mock(Player.class);
			Mockito.when(player.getId()).thenReturn(String.valueOf(i));
			players.add(player);
		}
		return players;
	}

	protected Node createMockedNode(int x, int y) {
		return createMockedNode(x, y, "node" + x + "," + y, "player1");
	}

	protected Node createMockedNode(int x, int y, String id, String playerId) {
		Node node = mock(Node.class);
		when(node.getId()).thenReturn(id);
		when(node.getOccupantPlayerId()).thenReturn(playerId);
		when(node.getXCoordinate()).thenReturn(x);
		when(node.getYCoordinate()).thenReturn(y);
		return node;
	}
}
