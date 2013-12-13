package test.othello.board;

import game.othello.board.StandardNode;

import java.util.Observable;
import java.util.Observer;

import org.junit.Assert;
import org.junit.Test;

public class NodeObserverTest {

	@Test
	public void notifyOnMark() {
		StandardNode node = new StandardNode(0, 0, "before");
		TestObserver observer = new TestObserver();
		node.addObserver(observer);

		node.mark("after");

		Assert.assertTrue(observer.observable instanceof StandardNode);
		Assert.assertTrue(observer.obj instanceof String);

		StandardNode node2 = (StandardNode) observer.observable;
		String playerId = (String) observer.obj;

		Assert.assertTrue(node == node2);
		Assert.assertEquals("before", playerId);
	}

	private class TestObserver implements Observer {
		Observable observable;
		Object obj;

		@Override
		public void update(Observable observable, Object obj) {
			this.obj = obj;
			this.observable = observable;
		}
	}

}
