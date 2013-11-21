package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

class BasicOthello implements Othello {

	private final BoardHandler boardHandler;
	private final PlayerHandler playerHandler;

	public BasicOthello(Board board, Player playerOne, Player playerTwo) {
		boardHandler = new BoardHandler(board);
		playerHandler = new PlayerHandler(playerOne, playerTwo);
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
		Node bestStartNode = findBestMoveForCurrentPlayer();
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

	private Node findBestMoveForCurrentPlayer() {
		int maxFlips = 0;
		Node maxFlipsArg = null;
		for (Node node : getBoard().getNodes()) {
			if (!isMoveValid(getPlayerInTurn().getId(), node.getId()))
				continue;
			List<Node> currentMove = getNodesToSwap(getPlayerInTurn().getId(), node.getId());
			if (currentMove.size() > maxFlips) {
				maxFlipsArg = node;
				maxFlips = currentMove.size();
			}
		}
		return maxFlipsArg;
	}
}
