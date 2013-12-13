package test.othello;

import game.logic.StandardRuleStrategy;
import game.othello.OthelloTheGame;
import game.othello.TurnHandler;
import game.othello.board.StandardBoard;
import game.othello.player.OthelloPlayer;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TurnHandlerTest {

	@Test
	public void CorrectPlayerInTurnMovesAlwaysPossible() throws Exception {
		Player mockedPlayer1 = Mockito.mock(OthelloPlayer.class);
		Player mockedPlayer2 = Mockito.mock(OthelloPlayer.class);
		Mockito.when(mockedPlayer1.getId()).thenReturn("1");
		Mockito.when(mockedPlayer1.getType()).thenReturn(Player.Type.HUMAN);
		Mockito.when(mockedPlayer2.getId()).thenReturn("2");
		Mockito.when(mockedPlayer2.getType()).thenReturn(Player.Type.HUMAN);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(mockedPlayer1);
		players.add(mockedPlayer2);

		StandardBoard board = Mockito.mock(StandardBoard.class);

		StandardRuleStrategy rules = Mockito.mock(StandardRuleStrategy.class);
		Mockito.when(rules.hasValidMove("1")).thenReturn(true);
		Mockito.when(rules.hasValidMove("2")).thenReturn(true);
		Mockito.when(rules.isMoveValid("1", "1")).thenReturn(true);
		Mockito.when(rules.isMoveValid("2", "1")).thenReturn(true);
		Mockito.when(rules.move("1", "1")).thenReturn(new ArrayList<Node>());
		Mockito.when(rules.move("2", "1")).thenReturn(new ArrayList<Node>());

		Constructor<OthelloTheGame> othelloConstructor = OthelloTheGame.class.getDeclaredConstructor(Board.class,
				List.class, StandardRuleStrategy.class, Score.class, TurnHandler.class);
		othelloConstructor.setAccessible(true);

		Score mockedScore = Mockito.mock(Score.class);

		Othello o = (Othello) othelloConstructor.newInstance(board, players, rules, mockedScore, new TurnHandler(
				players, rules));

		o.start("1");
		Assert.assertEquals("1", o.getPlayerInTurn().getId());
		o.move("1", "1");
		Assert.assertEquals("2", o.getPlayerInTurn().getId());
		o.move("2", "1");
		Assert.assertEquals("1", o.getPlayerInTurn().getId());
		o.move("1", "1");
		Assert.assertEquals("2", o.getPlayerInTurn().getId());
	}
}
