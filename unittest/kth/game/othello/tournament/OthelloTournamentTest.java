package kth.game.othello.tournament;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.MockingBase;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.player.Player;

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

		OthelloTournament.createTournamentFromPlayers(players, resultDisplay, factory, schedule);

		int size = 0;
		while (!schedule.isOver()) {
			schedule.nextMatchUp();
			size++;
		}

		assertEquals(6, size);
	}

	@Test
	public void testCreateCorrectOthelloInstanceWithTwoComputers() {
		Player computer = Mockito.mock(Player.class);
		Mockito.when(computer.getType()).thenReturn(Player.Type.COMPUTER);
		Player computer2 = Mockito.mock(Player.class);
		Mockito.when(computer2.getType()).thenReturn(Player.Type.COMPUTER);

		List<Player> players = new ArrayList<Player>();
		players.add(computer);
		players.add(computer2);

		OthelloFactory factory = Mockito.mock(OthelloFactory.class);
		Othello othelloInstance = Mockito.mock(Othello.class);
		Mockito.when(factory.createComputerGameOnClassicalBoard()).thenReturn(othelloInstance);

		Mockito.when(othelloInstance.getPlayers()).thenReturn(players);

		assertNotNull(OthelloTournament.createCorrectMatchUp(factory, computer, computer2));
	}

	@Test
	public void testCreateCorrectOthelloInstanceWithTwoHumans() {
		Player human = Mockito.mock(Player.class);
		Mockito.when(human.getType()).thenReturn(Player.Type.HUMAN);
		Player human2 = Mockito.mock(Player.class);
		Mockito.when(human2.getType()).thenReturn(Player.Type.HUMAN);

		List<Player> players = new ArrayList<Player>();
		players.add(human);
		players.add(human2);

		OthelloFactory factory = Mockito.mock(OthelloFactory.class);
		Othello othelloInstance = Mockito.mock(Othello.class);
		Mockito.when(factory.createHumanGameOnOriginalBoard()).thenReturn(othelloInstance);

		Mockito.when(othelloInstance.getPlayers()).thenReturn(players);

		assertNotNull(OthelloTournament.createCorrectMatchUp(factory, human, human2));
	}

	@Test
	public void testCreateCorrectOthelloInstanceWithOneHumanAndOneComputer() {
		Player human = Mockito.mock(Player.class);
		Mockito.when(human.getType()).thenReturn(Player.Type.HUMAN);
		Player computer = Mockito.mock(Player.class);
		Mockito.when(computer.getType()).thenReturn(Player.Type.COMPUTER);

		List<Player> players = new ArrayList<Player>();
		players.add(human);
		players.add(computer);

		OthelloFactory factory = Mockito.mock(OthelloFactory.class);
		Othello othelloInstance = Mockito.mock(Othello.class);
		Mockito.when(factory.createHumanVersusComputerGameOnOriginalBoard()).thenReturn(othelloInstance);

		Mockito.when(othelloInstance.getPlayers()).thenReturn(players);

		assertNotNull(OthelloTournament.createCorrectMatchUp(factory, human, computer));
	}

}
