package kth.game.othello.board;

import java.util.List;

class BasicBoard implements Board {
	
	final List<Node> nodes;  
	
	BasicBoard (List<Node> nodes) {
		this.nodes = nodes;
	}

	@Override public List<Node> getNodes() {
		return nodes;
	}
}
