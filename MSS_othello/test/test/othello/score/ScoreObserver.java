package test.othello.score;

import game.logic.StandardRuleStrategy;
import game.othello.TurnHandler;
import game.othello.board.StandardNode;
import game.othello.score.Score_impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	@Test
	public void testMinusScoreWhenPlayerHasNoMove() {
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);

		Mockito.when(p1.getId()).thenReturn("1");
		Mockito.when(p2.getId()).thenReturn("2");

		List<Player> players = Arrays.asList(p1, p2);

		Board board = Mockito.mock(Board.class);
		StandardRuleStrategy rules = Mockito.mock(StandardRuleStrategy.class);

		Mockito.when(rules.hasValidMove("1")).thenReturn(false);
		Score_impl score = new Score_impl(board);
		score.addPlayers(players);
		TurnHandler turnHandler = new TurnHandler(players, rules, score);
		turnHandler.start("1");

		turnHandler.getPlayerInTurn();

		Assert.assertEquals(-2, score.getPoints("1"));
	}

	@Test
	public void testDoubleScoreAtBoundaries() {
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);

		Mockito.when(p1.getId()).thenReturn("1");
		Mockito.when(p2.getId()).thenReturn("2");

		Board board = Mockito.mock(Board.class);
		StandardNode boundarieNode = Mockito.mock(StandardNode.class);
		Mockito.when(boundarieNode.getXCoordinate()).thenReturn(0);
		Mockito.when(boundarieNode.getYCoordinate()).thenReturn(0);
		Mockito.when(boundarieNode.getOccupantPlayerId()).thenReturn("1");

		StandardNode middleNode = Mockito.mock(StandardNode.class);
		Mockito.when(middleNode.getXCoordinate()).thenReturn(1);
		Mockito.when(middleNode.getYCoordinate()).thenReturn(1);
		Mockito.when(middleNode.getOccupantPlayerId()).thenReturn("2");

		Mockito.when(board.hasNode(0, 0)).thenReturn(true);
		Mockito.when(board.hasNode(0, 1)).thenReturn(true);
		Mockito.when(board.hasNode(0, 2)).thenReturn(true);
		Mockito.when(board.hasNode(1, 0)).thenReturn(true);
		Mockito.when(board.hasNode(1, 1)).thenReturn(true);
		Mockito.when(board.hasNode(1, 2)).thenReturn(true);
		Mockito.when(board.hasNode(2, 0)).thenReturn(true);
		Mockito.when(board.hasNode(2, 1)).thenReturn(true);
		Mockito.when(board.hasNode(2, 2)).thenReturn(true);
		Score_impl score = new Score_impl(board);
		score.addPlayers(Arrays.asList(p1, p2));

		score.update(middleNode, null);
		Assert.assertEquals(1, score.getPoints("2"));
		score.update(boundarieNode, null);
		Assert.assertEquals(2, score.getPoints("1"));
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
