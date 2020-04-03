package com.jeremy.ardent.world.entity;

import com.badlogic.gdx.graphics.Texture;

public enum EntityType {

	PLAYER("badlogic.jpg", 1.0f, 1.0f, 100.0f);

	private float width, height;
	private float maxHealth;
	private Texture texture;

	private EntityType(String textureName, float width, float height, float maxHealth) {
		this.texture = new Texture(textureName);
		this.width = width;
		this.height = height;
		this.maxHealth = maxHealth;
	}

	public Texture getTexture() {
		return texture;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getMaxHealth() {
		return maxHealth;
	}

}
