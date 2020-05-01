package com.jeremy.ardent.level;

public class TileLocation {

	public final Level level;
	public final int x, y;

	TileLocation(Level level, int x, int y) {
		this.level = level;
		this.x = x;
		this.y = y;
	}

	TileLocation(int x, int y) {
		this(null, x, y);
	}

	public TileLocation(Level level, TileLocation location) {
		this(level, location.x, location.y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TileLocation)) return false;
		TileLocation key = (TileLocation) o;
		return x == key.x && y == key.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}

}
