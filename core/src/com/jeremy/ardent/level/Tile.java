package com.jeremy.ardent.level;

import com.badlogic.gdx.math.Rectangle;

public class Tile {

	private float x, y;
	private Rectangle collision;

	public Tile(float x, float y) {
		this.x = x;
		this.y = y;
		collision = new Rectangle(0.0f, 0.0f, 1.0f, 1.0f);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rectangle getCollision() {
		return collision;
	}

}
