package com.jeremy.ardent.level;

public class Location {

	public final Level level;
	public final float x, y;

	public Location(Level level, float x, float y) {
		this.level = level;
		this.x = x;
		this.y = y;
	}

	public Location(float x, float y) {
		this(null, x, y);
	}

	public Location(Level level, Location location) {
		this(level, location.x, location.y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Location)) return false;
		Location key = (Location) o;
		return x == key.x && y == key.y;
	}

	@Override
	public int hashCode() {
		float result = x;
		result = 31 * result + y;
		return Float.floatToIntBits(result);
	}

}
