package tournamentorder;

import game.othello.OthelloCreatorImpl;
import game.othello.board.BoardCreatorImpl;
import game.othello.board.NodeCreatorImpl;
import game.othello.player.PlayerCreatorImpl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;
import match.StandardMatch;

/**
 * The order that the matches is generated for 3 players is First: player 1 vs
 * player 2, Second: player 1 vs player 3, Third: player 2 vs player 1, etc
 * 
 * @author jonac
 * 
 */
public class StandardTournamentOrder implements TournamentOrder {

	private List<Player> players;
	private Iterator<Player> player1Iterator;
	private Iterator<Player> player2Iterator;
	private Deque<StandardMatch> matchQueue = new ArrayDeque<StandardMatch>();
	private Player player1;
	private Player player2;

	public StandardTournamentOrder(List<Player> players) {
		this.players = players;
		player1Iterator = this.players.iterator();
		player2Iterator = this.players.iterator();
		player1 = player1Iterator.next();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tournamentorder.TournamentOrder#getNextMatch()
	 */
	@Override
	public StandardMatch getNextMatch() {
		if (matchQueue.isEmpty()) {
			if (!addMatches()) {
				return null;
			}
		}
		return matchQueue.pop();
	}

	// Add new matches return false if no new matches exists
	private boolean addMatches() {
		ArrayList<Player> currentPlayers = new ArrayList<Player>();
		if (!player1Iterator.hasNext() && !player2Iterator.hasNext()) {
			return false;
		} else if (!player2Iterator.hasNext() && player1Iterator.hasNext()) {
			player1 = player1Iterator.next();
			player2Iterator = players.iterator();

		}
		player2 = player2Iterator.next();
		if (player1.equals(player2) && player2Iterator.hasNext()) {
			player2 = player2Iterator.next();
		} else if (!player2Iterator.hasNext() && !player1Iterator.hasNext()) {
			return false;
		}

		currentPlayers.add(player1);
		currentPlayers.add(player2);

		BoardFactory boardFactory = new BoardFactory(new NodeCreatorImpl(), new BoardCreatorImpl());
		OthelloFactory othelloFactory = new OthelloFactory(new OthelloCreatorImpl(), boardFactory,
				new PlayerCreatorImpl());
		Othello othello = othelloFactory.createGame(boardFactory.getQuadraticBoard(8, currentPlayers), currentPlayers);

		matchQueue.add(new StandardMatch(othello, currentPlayers, player1.getId()));
		return true;
	}
}
