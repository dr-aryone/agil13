package kth.game.othello.score;

import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

import kth.game.othello.MockingBase;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;

public class ScoreTest extends MockingBase {

	@Test
	public void testScoreObservation() {
		List<Player> players = createMockedPlayers(3);
		BasicScore score = new BasicScore(players);
		final AtomicBoolean updateTriggered = new AtomicBoolean(false);

		score.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				List<String> changedIds = Collections.singletonList("0");
				Assert.assertEquals(changedIds, arg);
				updateTriggered.set(true);
			}
		});

		score.increasePoints("0", 123);
		if (!updateTriggered.get())
			Assert.fail();
	}
}
