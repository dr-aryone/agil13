package kth.game.othello.board;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

import kth.game.othello.MockingBase;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest extends MockingBase {

	@Test
	public void testSwapObserving() {
		final BasicNode node = new BasicNode(0, 0);
		final AtomicBoolean updateTriggered = new AtomicBoolean(false);
		node.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				Assert.assertEquals(node, o);
				Assert.assertEquals("p1", arg);
				updateTriggered.set(true);
			}
		});
		node.setOccupantPlayerId("p1");
		if (!updateTriggered.get())
			Assert.fail();
	}
}
