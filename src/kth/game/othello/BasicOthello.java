package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

class BasicOthello implements Othello {

	private final BoardHandler boardHandler;
	private final PlayerHandler playerHandler;
	private final MoveHandler moveHandler;
	private final Rules rules;
	private Score score;

	public BasicOthello(Board board, List<Player> players) {
		this(new BoardHandler(board), new PlayerHandler(players));
	}

	BasicOthello(BoardHandler boardHandler, PlayerHandler playerHandler) {
		this.boardHandler = boardHandler;
		this.playerHandler = playerHandler;
		this.rules = new BasicRules(boardHandler);
		this.moveHandler = new MoveHandler(rules, playerHandler, boardHandler);
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
		return getPlayerInTurn() != null;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return rules.isMoveValid(playerId, nodeId);
	}

	@Override
	public List<Node> move() {
		return moveHandler.move();
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		return moveHandler.move(playerId, nodeId);
	}

	@Override
	public void start() {
		start(playerHandler.randomPlayer().getId());
	}

	@Override
	public void start(String playerId) {
		playerHandler.setPlayerInTurn(playerId);
		List<Player> players = playerHandler.getAllPlayers();
		boardHandler.placeInitialBricks(players.get(0), players.get(1));
	}

	@Override
	public Score getScore() {
		// TODO Auto-generated method stub
		return null;
	}
}
