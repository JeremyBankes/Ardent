package com.jeremy.ardent.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jeremy.ardent.world.entity.systems.RenderingSystem;

public class ClientWorld {

	private static final float UNITS_ACROSS = 16.0f;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Stage stage;
	private Engine engine;
	private World world;

	private Box2DDebugRenderer debugRenderer;

	public ClientWorld() {
		camera = new OrthographicCamera();
		FitViewport view = new FitViewport(UNITS_ACROSS, UNITS_ACROSS * Gdx.graphics.getHeight() / Gdx.graphics.getWidth(), camera);

		batch = new SpriteBatch();
		stage = new Stage(view, batch);
		world = new World(new Vector2(0, 0), true);

		debugRenderer = new Box2DDebugRenderer();

		engine = new Engine();
		engine.addSystem(new RenderingSystem());

		camera.position.set(0, 0, 0);
	}

	public void update(float deltaTime) {
		camera.update();
		stage.act();
		stage.draw();
		debugRenderer.render(world, stage.getCamera().combined);
		engine.update(deltaTime);
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
	}

	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	public Stage getStage() {
		return stage;
	}

	public Engine getEntityEngine() {
		return engine;
	}

	public World getPhysicsWorld() {
		return world;
	}

}
