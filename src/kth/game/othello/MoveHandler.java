package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

class MoveHandler {
	private final PlayerHandler playerHandler;
	private final BoardHandler boardHandler;
	private final Rules rules;

	MoveHandler(Rules rules, PlayerHandler playerHandler, BoardHandler boardHandler) {
		this.rules = rules;
		this.playerHandler = playerHandler;
		this.boardHandler = boardHandler;
	}

	public Player getPlayerInTurn() {
		if (rules.hasValidMove(playerHandler.getPlayerInTurn().getId())) {
			return playerHandler.getPlayerInTurn();
		} else if (rules.hasValidMove(playerHandler.getNextPlayer().getId())) {
			playerHandler.changePlayer();
			return playerHandler.getPlayerInTurn();
		}
		return null;
	}

	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		if (!rules.isMoveValid(playerId, nodeId)) {
			throw new IllegalArgumentException("Move is not valid");
		}
		List<Node> nodesToSwap = rules.getNodesToSwap(playerId, nodeId);
		nodesToSwap.add(boardHandler.getNode(nodeId));
		for (Node node : nodesToSwap)
			boardHandler.occupyNodeByPlayer(node, playerHandler.getPlayer(playerId));
		playerHandler.changePlayer();
		return nodesToSwap;
	}

}
