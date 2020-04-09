package com.jeremy.ardent;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jeremy.ardent.entity.system.RemoveSystem;
import com.jeremy.ardent.entity.system.SpawnSystem;
import com.jeremy.ardent.input.InputHandler;

public class Core {

	public static final Vector2 GRAVITY = new Vector2(0.0f, 10.0f);

	private World world;
	private Engine engine;

	private SpriteBatch batch;

	private InputHandler inputHandler;

	public Core() {
		world = new World(GRAVITY, true);
		engine = new Engine();

		batch = new SpriteBatch();

		inputHandler = new InputHandler();

		engine.addSystem(new SpawnSystem());
		engine.addSystem(new RemoveSystem(this));

		Gdx.input.setInputProcessor(inputHandler);
	}

	void render(float deltaTime) {
		engine.update(deltaTime);
		world.step(deltaTime, 8, 4);
		batch.begin();
		batch.end();
	}

	public void addEntity(Entity entity) {
		engine.addEntity(entity);
	}

	public void removeEntity(Entity entity) {
		engine.removeEntity(entity);
	}

	public void dispose() {
		world.dispose();
		batch.dispose();
	}

	public World getWorld() {
		return world;
	}

	public Engine getEngine() {
		return engine;
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}

}
