package kth.game.othello;

import java.util.Arrays;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

class BasicOthello implements Othello {

	private final BoardHandler boardHandler;
	private final PlayerHandler playerHandler;
	private final Rules rules;

	public BasicOthello(Board board, Player playerOne, Player playerTwo) {
		this(board, Arrays.asList(playerOne, playerTwo));
	}

	public BasicOthello(Board board, List<Player> players) {
		this(new BoardHandler(board), new PlayerHandler(players));
	}

	BasicOthello(BoardHandler boardHandler, PlayerHandler playerHandler) {
		this.boardHandler = boardHandler;
		this.playerHandler = playerHandler;
		this.rules = new BasicRules();
	}

	@Override
	public Board getBoard() {
		return boardHandler.getBoard();
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return boardHandler.getNodesToSwap(playerId, nodeId);
	}

	@Override
	public Player getPlayerInTurn() {
		if (hasValidMove(playerHandler.getPlayerInTurn().getId())) {
			return playerHandler.getPlayerInTurn();
		} else if (hasValidMove(playerHandler.getNextPlayer().getId())) {
			playerHandler.changePlayer();
			return playerHandler.getPlayerInTurn();
		}
		return null;
	}

	@Override
	public List<Player> getPlayers() {
		return playerHandler.getAllPlayers();
	}

	@Override
	public boolean hasValidMove(String playerId) {
		for (Node node : getBoard().getNodes()) {
			if (isMoveValid(playerId, node.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isActive() {
		return getPlayerInTurn() != null;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return !boardHandler.getNode(nodeId).isMarked() && !getNodesToSwap(playerId, nodeId).isEmpty();
	}

	@Override
	public List<Node> move() {
		if (getPlayerInTurn().getType() != Player.Type.COMPUTER) {
			throw new IllegalStateException("Current player is not a computer");
		}
		Node bestStartNode = getPlayerInTurn().getMoveStrategy().move(getPlayerInTurn().getId(), rules, getBoard());
		return move(getPlayerInTurn().getId(), bestStartNode.getId());
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		if (!isMoveValid(playerId, nodeId)) {
			throw new IllegalArgumentException("Move is not valid");
		}
		List<Node> nodesToSwap = getNodesToSwap(playerId, nodeId);
		nodesToSwap.add(boardHandler.getNode(nodeId));
		for (Node node : nodesToSwap)
			boardHandler.occupyNodeByPlayer(node, playerHandler.getPlayer(playerId));
		playerHandler.changePlayer();
		return nodesToSwap;
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
