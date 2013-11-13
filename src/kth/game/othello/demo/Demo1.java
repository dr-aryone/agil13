package kth.game.othello.demo;

import java.util.List;

import kth.game.othello.BasicOthelloFactory;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

class Demo1 {

	OthelloFactory factory = new BasicOthelloFactory();

	public static void main(String[] args) {
		new Demo1().play();
	}

	private void play() {
		Othello othello = factory.createComputerGameOnClassicalBoard();
		othello.start();
		play(othello);
		Player winner = determineWinner(othello);
		System.out.println(winner.getName() + " Won!");
	}

	private Player determineWinner(Othello othello) {
		int player0Score = 0, player1Score = 0;
		List<Player> players = othello.getPlayers();
		for (Node node : othello.getBoard().getNodes()) {
			if (!node.isMarked())
				continue;
			if (node.getOccupantPlayerId().equals(players.get(0).getId())) {
				player0Score++;
			} else if (node.getOccupantPlayerId().equals(players.get(1).getId())) {
				player1Score++;
			}
		}
		return highestScore(player0Score, player1Score, players);
	}

	private Player highestScore(int player0Score, int player1Score, List<Player> players) {
		if (player0Score > player1Score) {
			return players.get(0);
		} else if (player1Score > player0Score) {
			return players.get(1);
		} else {
			return null;
		}
	}

	private void play(Othello othello) {
		while (othello.isActive()) {
			othello.move();
		}
	}
}
