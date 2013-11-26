package kth.game.othello;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.BasicNode;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MockingBase {

	protected List<Player> createMockedPlayers(int numberOfPlayers) {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < numberOfPlayers; i++) {
			Player player = Mockito.mock(Player.class);
			Mockito.when(player.getId()).thenReturn(String.valueOf(i));
			players.add(player);
		}
		return players;
	}

	protected Node createMockedNode(int x, int y) {
		return createMockedNode(x, y, x + "," + y, null);
	}

	protected BasicNode createMockedNode(int x, int y, String id, String playerId) {
		final BasicNode node = mock(BasicNode.class);
		when(node.getId()).thenReturn(id);
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				String newId = (String) invocation.getArguments()[0];
				when(node.getOccupantPlayerId()).thenReturn(newId);
				return null;
			}
		}).when(node).setOccupantPlayerId(Mockito.anyString());

		when(node.getOccupantPlayerId()).thenReturn(playerId);
		when(node.getXCoordinate()).thenReturn(x);
		when(node.getYCoordinate()).thenReturn(y);
		when(node.isMarked()).thenReturn(playerId != null);
		return node;
	}

	protected Board createMockedBoard(int size) {
		Board board = mock(Board.class);
		List<Node> nodes = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Node mockedNode = createMockedNode(i, i);
			nodes.add(mockedNode);
			when(board.getNode(i, i)).thenReturn(mockedNode);
		}
		when(board.getNodes()).thenReturn(nodes);
		return board;
	}
}
