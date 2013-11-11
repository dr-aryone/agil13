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
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

}
