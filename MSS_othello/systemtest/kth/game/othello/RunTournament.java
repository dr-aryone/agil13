package kth.game.othello;

import java.util.Scanner;

import tournament.Tournament;
import tournament.TournamentFactory;

public class RunTournament {
	private static final int WITH_VIEW = 1;
	private static final int NO_VIEW = 2;

	public static void main(String[] args) {
		TournamentFactory factory = new TournamentFactory();
		Scanner reader = new Scanner(System.in);
		Tournament tournament;
		while (true) {
			System.out.println("Please choose if you want a view or not");
			System.out.println(WITH_VIEW + ": Using view");
			System.out.println(NO_VIEW + ": No view");
			int choice = reader.nextInt();
			if (choice == WITH_VIEW) {
				tournament = factory.createTournamentWithThreeComputersUsingView(50, 50);
				break;
			} else if (choice == NO_VIEW) {
				tournament = factory.createTournamentWithThreeComputersNoView();
				break;
			} else {
				System.out.println("Please choose a number!");
			}
		}

		tournament.play();

		System.out.println(tournament.getTournamentResults());
	}
}
