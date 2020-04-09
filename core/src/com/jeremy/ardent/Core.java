package com.jeremy.ardent;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Core {

	public static final Vector2 GRAVITY = new Vector2(0.0f, 10.0f);

	private World world;
	private Engine engine;

	private SpriteBatch batch;

	public Core() {
		world = new World(GRAVITY, true);
		engine = new Engine();
	}

	void render(float deltaTime) {
		world.step(deltaTime, 8, 4);
	}

	public void addEntity(Entity entity) {
		engine.addEntity(entity);
	}

	public void removeEntity(Entity entity) {
		engine.removeEntity(entity);
	}

	public void dispose() {
		world.dispose();
	}

	public World getWorld() {
		return world;
	}

	public Engine getEngine() {
		return engine;
	}

}
