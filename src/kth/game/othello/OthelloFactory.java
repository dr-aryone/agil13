package kth.game.othello;

/**
 * A factory for producing othello games.
 * 
 * @author Tomas Ekholm
 */
public interface OthelloFactory {

	/**
	 * Create an Othello game with an original board with two computer.
	 * 
	 * @return An Othello game
	 */
	public Othello createComputerGameOnClassicalBoard();

	/**
	 * Create an Othello game with an original board with two humans.
	 * 
	 * @return An Othello game
	 */
	public Othello createHumanGameOnOriginalBoard();

	/**
	 * Creates an Othello game with an original board with one computer playing against one human. The computer will be
	 * the first player in the list of players.
	 * 
	 * @return An Othello game
	 */
	public Othello createHumanVersusComputerGameOnOriginalBoard();

}