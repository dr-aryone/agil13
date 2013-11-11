package kth.game.othello.player;

abstract class AbstractPlayer implements Player {
	private final String id;
	private final String name;
	private final Type type;

	public AbstractPlayer(String id, String name, Type type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
