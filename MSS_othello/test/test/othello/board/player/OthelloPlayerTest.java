package test.othello.board.player;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import game.othello.player.PlayerCreatorImpl;
import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.MoveStrategy;

import org.junit.Assert;
import org.junit.Test;

public class OthelloPlayerTest {

	private PlayerCreatorImpl playerCreator = new PlayerCreatorImpl();

	@Test
	public void createComputerPlayer() {
		MoveStrategy mockedMoveStrategy = mock(MoveStrategy.class);

		Player player = playerCreator.createComputerPlayer("Computer", mockedMoveStrategy);
		Assert.assertEquals(Player.Type.COMPUTER, player.getType());
	}

	@Test
	public void createHumanPlayer() {
		Player player = playerCreator.createHumanPlayer("Name");
		Assert.assertEquals(Player.Type.HUMAN, player.getType());
		Assert.assertEquals("Name", player.getName());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void createHumanPlayerWithMoveStrategy() {
		MoveStrategy mockedMoveStrategy = mock(MoveStrategy.class);

		Player player = playerCreator.createHumanPlayer("Human");
		player.setMoveStrategy(mockedMoveStrategy);
	}

	@Test
	public void computerChangeStrategy() {
		MoveStrategy mockedMoveStrategy = mock(MoveStrategy.class);
		when(mockedMoveStrategy.getName()).thenReturn("first");
		Player player = playerCreator.createComputerPlayer("Computer", mockedMoveStrategy);

		Assert.assertEquals("first", player.getMoveStrategy().getName());

		MoveStrategy mockedMoveStrategy2 = mock(MoveStrategy.class);
		when(mockedMoveStrategy2.getName()).thenReturn("second");

		player.setMoveStrategy(mockedMoveStrategy2);

		Assert.assertEquals("second", player.getMoveStrategy().getName());
	}

}
