package kth.game.othello.tournament;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.MockingBase;
import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreItem;

import org.junit.Test;
import org.mockito.Mockito;

public class ResultsTest extends MockingBase {

	@Test
	public void testGetPointsForPlayer() {
		Score score = Mockito.mock(Score.class);
		Othello othello = Mockito.mock(Othello.class);
		Mockito.when(othello.getScore()).thenReturn(score);
		List<ScoreItem> scores = new ArrayList<>();

		ScoreItem scoreItem1 = Mockito.mock(ScoreItem.class);
		Mockito.when(scoreItem1.getPlayerId()).thenReturn("1");
		Mockito.when(scoreItem1.getScore()).thenReturn(1);

		scores.add(scoreItem1);

		Mockito.when(score.getPlayersScore()).thenReturn(scores);

		Player player = Mockito.mock(Player.class);
		Mockito.when(player.getId()).thenReturn("1");

		Results results = new Results();
		results.registerGame(othello);

		assertEquals(new Integer(2), results.getPointsForPlayer(player));
	}
}
