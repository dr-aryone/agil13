package game.othello.player;

import java.util.HashMap;
import java.util.Random;

/**
 * Responsibility of this class is to make sure each player has a unique
 * color/marker.
 * 
 * @author Jonas Carlsson
 * 
 */
public class PlayerColour {

	private static PlayerColour instance = new PlayerColour();
	private int NextColor = 0;
	private String[] colorsList = { "X", "O", "A", "B" };
	private HashMap<String, String> playerColor = new HashMap<String, String>();

	/**
	 * Create a standard game which can support 2 players
	 */
	private PlayerColour() {
	}

	public static PlayerColour createPlayerColour() {
		return instance;
	}

	/**
	 * Generate random markers for the given amount of players
	 * 
	 * @param NumberOfPlayers How many players are in the game
	 */
	public void createRandomList(int NumberOfPlayers) {
		Random random = new Random();
		colorsList = new String[NumberOfPlayers];
		int x = 0;
		for (int i = 0; i < NumberOfPlayers; i++) {
			x = random.nextInt(79);
			colorsList[i] = new String(Character.toChars(x + 48));
		}

	}

	/**
	 * Create from given list
	 * 
	 * @param colourList list of possible colors.
	 */
	public void createFromList(String[] colourList) {
		this.colorsList = colourList;
	}

	/**
	 * Get the color assigned to this player. If no color is assigned take a new
	 * from the pool.
	 * 
	 * @param playerId
	 * @return A color for this player
	 */
	public String getPlayerColor(String playerId) {
		String color = playerColor.get(playerId);
		if (color == null) {
			String newColor = colorsList[NextColor];
			NextColor = (NextColor + 1) % colorsList.length;
			// NextColor++;
			playerColor.put(playerId, newColor);
			return newColor;
		} else {
			return color;
		}
	}
}
