package kth.game.othello;

import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class OthelloTest extends MockingBase {

	@Test
	public void testObserverReactingToFinishedGame() {
		MoveHandler moveHandler = Mockito.mock(MoveHandler.class);
		List<Node> swapped = Collections.emptyList();
		Mockito.when(moveHandler.move(Mockito.anyString(), Mockito.anyString())).thenReturn(swapped);
		Othello othello = new BasicOthello(null, null, moveHandler, null, null);

		final AtomicBoolean updateTriggered = new AtomicBoolean(false);
		othello.addMoveObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				updateTriggered.set(true);
			}
		});

		othello.move(Mockito.anyString(), Mockito.anyString());

		if (!updateTriggered.get())
			Assert.fail();
	}

	@Test
	public void testObserverReactingToPerformedMove() {
		MoveHandler moveHandler = Mockito.mock(MoveHandler.class);
		List<Node> swapped = Collections.emptyList();
		Mockito.when(moveHandler.move(Mockito.anyString(), Mockito.anyString())).thenReturn(swapped);
		Othello othello = new BasicOthello(null, null, moveHandler, null, null);

		final AtomicBoolean updateTriggered = new AtomicBoolean(false);
		othello.addMoveObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				updateTriggered.set(true);
			}
		});

		othello.move(Mockito.anyString(), Mockito.anyString());

		if (!updateTriggered.get())
			Assert.fail();
	}
}
