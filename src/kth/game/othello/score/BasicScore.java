package kth.game.othello.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import kth.game.othello.player.Player;

class BasicScore extends Observable implements Score {

	private final List<ScoreItem> playerScores = new ArrayList<>();

	BasicScore(Player firstPlayer, Player... morePlayers) {
		playerScores.add(new ScoreItem(firstPlayer.getId(), 0));
		for (Player player : morePlayers) {
			playerScores.add(new ScoreItem(player.getId(), 0));
		}
	}

	@Override
	public List<ScoreItem> getPlayersScore() {
		return playerScores;
	}

	@Override
	public int getPoints(String playerId) {
		for (ScoreItem scoreItem : playerScores) {
			if (scoreItem.getPlayerId().equals(playerId)) {
				return scoreItem.getScore();
			}
		}
		return 0;
	}

}
