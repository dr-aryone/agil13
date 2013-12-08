package kth.game.othello.tournament;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.MockingBase;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;
import kth.game.othello.tournament.resultdisplay.ResultDisplay;

import org.junit.Test;
import org.mockito.Mockito;

public class OthelloTournamentTest extends MockingBase {

	@Test
	public void testCreateTournamentFromPlayersGeneratesCorrectNumberOfMatchUps() {
		Player player1 = Mockito.mock(Player.class);
		Player player2 = Mockito.mock(Player.class);
		Player player3 = Mockito.mock(Player.class);

		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		players.add(player3);

		Schedule schedule = new Schedule();

		ResultDisplay resultDisplay = Mockito.mock(ResultDisplay.class);

		OthelloFactory factory = Mockito.mock(OthelloFactory.class);

		BoardFactory boardFactory = Mockito.mock(BoardFactory.class);

		OthelloTournament.createTournamentFromPlayers(players, resultDisplay, factory, schedule, boardFactory);

		int size = 0;
		while (schedule.hasMoreMatchUps()) {
			schedule.nextMatchUp();
			size++;
		}

		assertEquals(6, size);
	}

}
