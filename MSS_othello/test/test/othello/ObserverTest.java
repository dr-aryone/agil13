package test.othello;

import game.logic.StandardRuleStrategy;
import game.othello.OthelloTheGame;
import game.othello.TurnHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

import org.junit.Test;
import org.mockito.Mockito;

public class ObserverTest {

	private OthelloTheGame reflectedOthelloConstruction(Board board, List<Player> players, StandardRuleStrategy rules,
			Score score, TurnHandler turnHandler) throws Exception {
		Constructor<OthelloTheGame> othelloConstructor = OthelloTheGame.class.getDeclaredConstructor(Board.class,
				List.class, StandardRuleStrategy.class, Score.class, TurnHandler.class);
		othelloConstructor.setAccessible(true);

		return othelloConstructor.newInstance(board, players, rules, score, turnHandler);
	}

	@Test
	public void gameIsFinished() throws Exception {
		TurnHandler mockedTurnHandler = Mockito.mock(TurnHandler.class);
		Mockito.when(mockedTurnHandler.getPlayerInTurn()).thenReturn(null);

		OthelloTheGame othello = this.reflectedOthelloConstruction(null, null, null, null, mockedTurnHandler);

		Observer mockedObserver = Mockito.mock(Observer.class);
		othello.addGameFinishedObserver(mockedObserver);

		othello.notifyObservers();

		Mockito.verify(mockedObserver, Mockito.times(1)).update(othello, null);

		othello.notifyObservers();

		Mockito.verify(mockedObserver, Mockito.times(2)).update(othello, null);
	}

	@Test
	public void gameIsNotFinished() throws Exception {
		TurnHandler mockedTurnHandler = Mockito.mock(TurnHandler.class);
		Player mockedPlayer = Mockito.mock(Player.class);
		Mockito.when(mockedTurnHandler.getPlayerInTurn()).thenReturn(mockedPlayer);

		OthelloTheGame othello = this.reflectedOthelloConstruction(null, null, null, null, mockedTurnHandler);

		Observer mockedObserver = Mockito.mock(Observer.class);
		othello.addGameFinishedObserver(mockedObserver);

		othello.notifyObservers();

		Mockito.verify(mockedObserver, Mockito.times(0)).update(othello, mockedPlayer);

		othello.notifyObservers();

		Mockito.verify(mockedObserver, Mockito.times(0)).update(othello, mockedPlayer);
	}

	@Test
	public void moveObserverTest() throws Exception {
		Observer mockedObserver = Mockito.mock(Observer.class);
		StandardRuleStrategy rules = new StandardRuleStrategy(null);
		rules.addObserver(mockedObserver);

		Method nodesHasChangedMethod = StandardRuleStrategy.class.getDeclaredMethod("nodesHasChanged", List.class);
		nodesHasChangedMethod.setAccessible(true);

		// Friendly :)
		List<Node> nodes = new ArrayList<Node>();

		nodesHasChangedMethod.invoke(rules, nodes);
		Mockito.verify(mockedObserver, Mockito.times(1)).update(rules, nodes);
		Mockito.verify(mockedObserver, Mockito.times(0)).update(rules, null);
		nodesHasChangedMethod.invoke(rules, nodes);
		Mockito.verify(mockedObserver, Mockito.times(2)).update(rules, nodes);
		Mockito.verify(mockedObserver, Mockito.times(0)).update(rules, null);
	}
}
