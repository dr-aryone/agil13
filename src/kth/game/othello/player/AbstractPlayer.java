package kth.game.othello.player;

import kth.game.othello.player.movestrategy.MoveStrategy;

abstract class AbstractPlayer implements Player {
	private final String id;
	private final String name;
	private final Type type;
	private MoveStrategy moveStrategy;

	AbstractPlayer(String id, String name, Type type, MoveStrategy moveStrategy) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.moveStrategy = moveStrategy;
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

	@Override
	public MoveStrategy getMoveStrategy() {
		return moveStrategy;
	}

	@Override
	public void setMoveStrategy(MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + id.hashCode();
		result = result * 31 + name.hashCode();
		result = result * 31 + type.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof AbstractPlayer))
			return false;
		AbstractPlayer a = (AbstractPlayer) obj;
		return id.equals(a.id) && name.equals(a.name) && type.equals(a.type);
	}
}
