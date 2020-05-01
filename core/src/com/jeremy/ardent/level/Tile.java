package com.jeremy.ardent.level;

import com.badlogic.gdx.math.Rectangle;

public class Tile {

	private TileLocation location;
	private Rectangle collision;

	public Tile(int x, int y) {
		this.location = new TileLocation(x, y);
		collision = new Rectangle(0.0f, 0.0f, 1.0f, 1.0f);
	}

	public int getX() {
		return location.x;
	}

	public int getY() {
		return location.y;
	}

	public Rectangle getCollision() {
		return collision;
	}

	TileLocation getLocation() {
		return location;
	}

}
