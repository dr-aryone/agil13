package kth.game.othello.board;

import java.util.Observable;
import java.util.Observer;

import kth.game.othello.MockingBase;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest extends MockingBase {

	@Test
	public void testSwapObserving() {
		final BasicNode node = new BasicNode(0, 0);
		node.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				Assert.assertEquals(node, o);
				Assert.assertEquals("p1", arg);
			}
		});
		node.setOccupantPlayerId("p1");
	}
}
