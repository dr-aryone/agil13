package kth.game.othello.board;

import java.util.List;

public class BasicBoardCreator implements BoardCreator {

	@Override
	public Board createBoard(List<Node> nodes) {
		return new BasicBoard(nodes);
	}
}
