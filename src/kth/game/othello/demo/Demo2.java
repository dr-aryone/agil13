package kth.game.othello.demo;

import java.util.List;
import java.util.Scanner;

import kth.game.othello.BasicOthelloFactory;
import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

class Demo2 {

	OthelloFactory factory = new BasicOthelloFactory();

	public static void main(String[] args) {
		new Demo2().play();
	}

	private void play() {
		Othello othello = factory.createHumanVersusComputerGameOnOriginalBoard();
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
		Scanner scanner = new Scanner(System.in);
		while (othello.isActive()) {
			System.out.println(othello.getBoard());
			if (othello.getPlayerInTurn().getType() == Player.Type.COMPUTER) {
				othello.move();
			} else {
				Coordinate coordinate = parseInput(scanner.nextLine());
				othello.move(othello.getPlayerInTurn().getId(),
						getNodeId(othello, coordinate.getX(), coordinate.getY()));
			}
		}
		scanner.close();
	}

	private Coordinate parseInput(String input) {
		String[] args = input.split(",");
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		Coordinate coordinate = new Coordinate(x, y);
		return coordinate;
	}

	private String getNodeId(Othello othello, int x, int y) {
		List<Node> nodes = othello.getBoard().getNodes();
		for (Node node : nodes) {
			if (node.getXCoordinate() == x && node.getYCoordinate() == y) {
				return node.getId();
			}
		}
		return null;
	}

	private class Coordinate {
		private final int x, y;

		public int getY() {
			return y;
		}

		public int getX() {
			return x;
		}

		Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
