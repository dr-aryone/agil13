package match;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

/**
 * Keep track of one round of othello in tournament mode
 * 
 * @author jonac
 * 
 */
public class StandardMatch implements Observer {
	Othello game;
	List<Player> players;
	boolean finished = false;
	Player winner;
	String FirstPlayer;

	/**
	 * @param game An othello instance which the match will be run on
	 * @param players A list with the players for this match
	 * @param whoStarts The player that starts this round
	 */
	public StandardMatch(Othello game, List<Player> players, String whoStarts) {
		this.game = game;
		this.players = players;
		this.game.addGameFinishedObserver(this);
		this.FirstPlayer = whoStarts;
	}

	/**
	 * Get the current instance of this round
	 * 
	 * @return Current instance of this round
	 */
	public Othello getGame() {
		return game;
	}

	/**
	 * Get the list of players
	 * 
	 * @return List of players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Return the player that started
	 * 
	 * @return The player that started
	 */
	public Player getFirstPlayer() {
		return game.getPlayer(FirstPlayer);
	}

	/**
	 * Return who won
	 * 
	 * @return The winner
	 */
	public Player getWinner() {
		return winner;
	}

	/**
	 * Is the game finished?
	 * 
	 * @return True if finished false otherwise
	 */
	public boolean isFinished() {
		return finished;
	}

	@Override
	public void update(Observable o, Object arg) {
		finished = true;
		Othello game = (Othello) o;
		Score score = game.getScore();
		String WinnerId = score.getPlayersScore().get(0).getPlayerId();
		winner = game.getPlayer(WinnerId);

	}
}
