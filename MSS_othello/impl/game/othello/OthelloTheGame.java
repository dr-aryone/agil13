package game.othello;

import game.logic.StandardRuleStrategy;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import kth.game.othello.Othello;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

/**
 * Main class for handling an Othello game, making moves on the board and
 * handling player turns
 * 
 * @author Filip Gauffin, filipgau@kth.se
 * @author Jonas Carlsson, jonas.ba.carlsson@gmail.com
 * 
 */
public class OthelloTheGame extends Observable implements Othello {

	private Board board;
	// Game logic
	private StandardRuleStrategy rules;
	private TurnHandler turnHandler;
	private List<Player> players;
	private Score score;

	private final UUID uuid;

	/**
	 * Create a new Othello Game
	 * 
	 * @param board the board to be used in the game
	 * @param players the players to play the game
	 */
	public OthelloTheGame(Board board, List<Player> players, Score score) {
		this.board = board;
		this.players = players;
		rules = new StandardRuleStrategy(board);
		this.score = score;
		this.uuid = UUID.randomUUID();
		turnHandler = new TurnHandler(players, rules);
	}

	@SuppressWarnings("unused")
	private OthelloTheGame(Board board, List<Player> players, StandardRuleStrategy rules, Score score,
			TurnHandler turnHandler) {
		this.board = board;
		this.players = players;
		this.rules = rules;
		this.score = score;
		this.uuid = UUID.randomUUID();
		this.turnHandler = turnHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#getBoard()
	 */
	public Board getBoard() {
		return this.board;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#getNodesToSwap(java.lang.String,
	 * java.lang.String)
	 */
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return rules.getNodesToSwap(playerId, nodeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#getPlayerInTurn()
	 */
	public Player getPlayerInTurn() {
		return turnHandler.getPlayerInTurn();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#getPlayers()
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#hasValidMove(java.lang.String)
	 */
	public boolean hasValidMove(String playerId) {
		return (turnHandler.playerInTurn(playerId) && rules.hasValidMove(playerId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#isActive()
	 */
	public boolean isActive() {
		return turnHandler.getPlayerInTurn() != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#isMoveValid(java.lang.String,
	 * java.lang.String)
	 */
	public boolean isMoveValid(String playerId, String nodeId) {
		return (turnHandler.playerInTurn(playerId) && rules.isMoveValid(playerId, nodeId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#move()
	 */
	public List<Node> move() {
		Player player = turnHandler.getPlayerInTurn();
		if (player.getType() != Player.Type.COMPUTER) {
			throw new IllegalArgumentException("Player in turn is not a computer");
		}
		Node node = player.getMoveStrategy().move(player.getId(), rules, board);
		return move(player.getId(), node.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#move(java.lang.String, java.lang.String)
	 */
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		if (!isMoveValid(playerId, nodeId)) {
			throw new IllegalArgumentException("Move is not valid");
		}
		turnHandler.changePlayer();
		List<Node> nodes = rules.move(playerId, nodeId);
		this.notifyObservers();
		return nodes;
	}

	@Override
	public void notifyObservers() {
		if (!this.isActive()) {
			this.setChanged();
			super.notifyObservers();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#start()
	 */
	public void start() {
		turnHandler.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#start(java.lang.String)
	 */
	public void start(String playerId) {
		turnHandler.start(playerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#getScore()
	 */
	public Score getScore() {
		return this.score;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(score.toString() + "\n");
		sb.append(board.toString() + "\n");
		sb.append("\n");
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#addGameFinishedObserver(java.util.Observer)
	 */
	public void addGameFinishedObserver(Observer observer) {
		this.addObserver(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#addMoveObserver(java.util.Observer)
	 */
	public void addMoveObserver(Observer observer) {
		this.rules.addObserver(observer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kth.game.othello.Othello#getId()
	 */
	public String getId() {
		return this.uuid.toString();
	}

	@Override
	public Player getPlayer(String playerId) {
		for (Player p : players) {
			if (p.getId().equals(playerId)) {
				return p;
			}
		}
		throw (new IllegalArgumentException("The player you were looking for did not exist"));
	}
}
