package kth.game.othello;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.BasicScore;
import kth.game.othello.score.Score;

class BasicOthello implements Othello {

	private static int nextId = 1;

	private final Observable moveObservable = new OthelloObservable();
	private final Observable gameFinishedObservable = new OthelloObservable();

	private final BoardHandler boardHandler;
	private final PlayerHandler playerHandler;
	private final MoveHandler moveHandler;
	private final Rules rules;
	private final BasicScore score;

	private final String id;

	BasicOthello(Board board, List<Player> players) {
		boardHandler = new BoardHandler(board);
		playerHandler = new PlayerHandler(players);
		score = new BasicScore(players);
		rules = new BasicRules(boardHandler);
		moveHandler = new MoveHandler(rules, playerHandler, boardHandler);
		id = getNextId();
	}

	BasicOthello(BoardHandler boardHandler, PlayerHandler playerHandler, MoveHandler moveHandler, Rules rules,
			BasicScore score) {
		this.boardHandler = boardHandler;
		this.playerHandler = playerHandler;
		this.rules = rules;
		this.moveHandler = moveHandler;
		this.score = score;
		id = getNextId();
	}

	@Override
	public Board getBoard() {
		return boardHandler.getBoard();
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return rules.getNodesToSwap(playerId, nodeId);
	}

	@Override
	public Player getPlayerInTurn() {
		return moveHandler.getPlayerInTurn();
	}

	@Override
	public List<Player> getPlayers() {
		return playerHandler.getAllPlayers();
	}

	@Override
	public boolean hasValidMove(String playerId) {
		return rules.hasValidMove(playerId);
	}

	@Override
	public boolean isActive() {
		boolean isActive = getPlayerInTurn() != null;
		if (!isActive) // game is over
			gameFinishedObservable.notifyObservers();
		return isActive;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return rules.isMoveValid(playerId, nodeId);
	}

	@Override
	public List<Node> move() {
		if (getPlayerInTurn().getType() != Player.Type.COMPUTER) {
			throw new IllegalStateException("Current player is not a computer");
		}
		Node bestStartNode = getPlayerInTurn().getMoveStrategy().move(getPlayerInTurn().getId(), rules,
				boardHandler.getBoard());
		return move(getPlayerInTurn().getId(), bestStartNode.getId());
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		List<Node> swappedNodes = moveHandler.move(playerId, nodeId);
		moveObservable.notifyObservers();
		return swappedNodes;
	}

	@Override
	public void start() {
		start(playerHandler.randomPlayer().getId());
	}

	@Override
	public void start(String playerId) {
		for (Node node : getBoard().getNodes()) {
			node.addObserver(score);
		}
		score.setInitialScore(getBoard());
		playerHandler.setPlayerInTurn(playerId);
	}

	@Override
	public Score getScore() {
		return score;
	}

	@Override
	public void addGameFinishedObserver(Observer observer) {
		gameFinishedObservable.addObserver(observer);
	}

	@Override
	public void addMoveObserver(Observer observer) {
		moveObservable.addObserver(observer);
	}

	@Override
	public String getId() {
		return id;
	}

	private static String getNextId() {
		return String.valueOf(nextId++);
	}

	private static class OthelloObservable extends Observable {
		@Override
		public void notifyObservers(Object arg) {
			setChanged();
			super.notifyObservers(arg);
		}
	}
}
