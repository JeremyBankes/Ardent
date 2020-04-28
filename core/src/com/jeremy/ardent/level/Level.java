package com.jeremy.ardent.level;

import java.util.HashMap;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.jeremy.ardent.entity.component.PhysicsComponent;
import com.jeremy.ardent.entity.listener.PhysicsListener;
import com.jeremy.ardent.entity.system.PhysicalRenderingSystem;

public class Level {

	public static final Vector2 GRAVITY = new Vector2(0.0f, -10.0f);
	public static final float MIN_METERS_HORIZONTAL = 20.0f;
	public static final float MIN_METERS_VERTICAL = 15.0f;
	public static final float MIN_ASPECT_RATIO = MIN_METERS_HORIZONTAL / MIN_METERS_VERTICAL;

	private World world;
	private Engine engine;
	private SpriteBatch batch;

	private OrthographicCamera camera;
	private Box2DDebugRenderer debugRenderer;

	private final HashMap<Location, Tile> tileMap;

	private CollisionMap collisionMap;

	public Level() {
		world = new World(GRAVITY, true);
		engine = new Engine();

		camera = new OrthographicCamera(20.0f, 15.0f);
		debugRenderer = new Box2DDebugRenderer();

		tileMap = new HashMap<>();
		collisionMap = new CollisionMap();

		batch = new SpriteBatch();

		engine.addEntityListener(Family.all(PhysicsComponent.class).get(), new PhysicsListener(world));
		engine.addSystem(new PhysicalRenderingSystem(batch));
	}

	public void update(float deltaTime) {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		engine.update(deltaTime);
		debugRenderer.render(world, camera.combined);
		world.step(deltaTime, 8, 8);
		batch.end();
	}

	public Tile getTile(int x, int y) {
		return tileMap.get(new Location(this, x, y));
	}

	public Tile getTile(Location location) {
		return getTile((int) location.x, (int) location.y);
	}

	public void setTile(Tile tile) {
		tileMap.put(new Location(this, tile.getX(), tile.getY()), tile);
		if (tile.getCollision() != null) {
			collisionMap.update(this, tile);
		}
	}

	public void addEntity(Entity entity) {
		engine.addEntity(entity);
	}

	public void removeEntity(Entity entity) {
		engine.removeEntity(entity);
	}

	public World getWorld() {
		return world;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void dispose() {
		batch.dispose();
		world.dispose();
	}

	public void resize(int width, int height) {
		float aspectRaio = (float) width / height;
		if (aspectRaio > MIN_ASPECT_RATIO) {
			getCamera().viewportWidth = MIN_METERS_HORIZONTAL;
			getCamera().viewportHeight = MIN_METERS_HORIZONTAL / aspectRaio;
		} else {
			getCamera().viewportWidth = MIN_METERS_VERTICAL * aspectRaio;
			getCamera().viewportHeight = MIN_METERS_VERTICAL;
		}
	}

}
