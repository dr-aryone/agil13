package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

/**
 * The responsibility of this entity is to perform logic which has to do with
 * performing moves.
 */
class MoveHandler {
	private final PlayerHandler playerHandler;
	private final BoardHandler boardHandler;
	private final Rules rules;

	MoveHandler(Rules rules, PlayerHandler playerHandler, BoardHandler boardHandler) {
		this.rules = rules;
		this.playerHandler = playerHandler;
		this.boardHandler = boardHandler;
	}

	/**
	 * Returns the Player object which turn it is, or if that player has no
	 * valid move, will return the next player in the queue which has a valid
	 * turn.
	 * 
	 * @return the next Player who has a valid move, or null if no player has a
	 *         valid move.
	 */
	public Player getPlayerInTurn() {
		Player initialPlayerInTurn = playerHandler.getPlayerInTurn();
		while (!rules.hasValidMove(playerHandler.getPlayerInTurn().getId())) {
			playerHandler.changePlayer();
			boolean hasCheckedAllPlayers = playerHandler.getPlayerInTurn().equals(initialPlayerInTurn);
			if (hasCheckedAllPlayers) {
				return null;
			}
		}
		return playerHandler.getPlayerInTurn();
	}

	/**
	 * Non-javadoc
	 * 
	 * @see Othello#move(String, String)
	 */
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
