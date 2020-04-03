package com.jeremy.ardent.world.entity;

import com.jeremy.ardent.world.ClientWorld;

public abstract class Creature extends ClientEntity {

	private String name;
	private String displayName;
	private float maxHealth;
	private float health;

	public Creature(ClientWorld world, float x, float y, EntityType type) {
		super(world, x, y, type);
		this.maxHealth = health = type.getMaxHealth();
		this.name = displayName = getClass().getSimpleName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}

}
