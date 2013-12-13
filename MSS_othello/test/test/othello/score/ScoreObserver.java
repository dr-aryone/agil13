package test.othello.score;

import game.othello.board.StandardNode;
import game.othello.score.Score_impl;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ScoreObserver {
	@Test
	@SuppressWarnings("unchecked")
	public void scoreObserver() {
		Player mockedPlayer1 = Mockito.mock(Player.class);
		Player mockedPlayer2 = Mockito.mock(Player.class);

		Mockito.when(mockedPlayer1.getId()).thenReturn("before");
		Mockito.when(mockedPlayer2.getId()).thenReturn("after");

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(mockedPlayer1);
		players.add(mockedPlayer2);
		Board board = Mockito.mock(Board.class);

		Score_impl score = new Score_impl(board);
		TestObserver observer = new TestObserver();
		score.addObserver(observer);
		score.addPlayers(players);

		StandardNode mockedNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedNode.getOccupantPlayerId()).thenReturn("after");

		String playerId = "before";

		score.update(mockedNode, playerId);

		Assert.assertTrue(observer.obj instanceof ArrayList);
		ArrayList<String> list = (ArrayList<String>) observer.obj;

		Assert.assertTrue(list.contains(playerId));
		Assert.assertTrue(list.contains(mockedNode.getOccupantPlayerId()));
	}

	private class TestObserver implements Observer {
		@SuppressWarnings("unused")
		Observable observable;
		Object obj;

		@Override
		public void update(Observable observable, Object obj) {
			this.obj = obj;
			this.observable = observable;
		}
	}
}
