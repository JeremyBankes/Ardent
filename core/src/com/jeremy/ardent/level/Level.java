package com.jeremy.ardent.level;

import java.util.HashMap;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.jeremy.ardent.entity.component.PhysicsComponent;
import com.jeremy.ardent.entity.listener.PhysicsListener;
import com.jeremy.ardent.entity.system.PhysicalRenderingSystem;
import com.jeremy.ardent.entity.system.PlayerMovementSystem;

public class Level {

	public static final float MIN_METERS_HORIZONTAL = 20.0f;
	public static final float MIN_METERS_VERTICAL = 15.0f;
	public static final float MIN_ASPECT_RATIO = MIN_METERS_HORIZONTAL / MIN_METERS_VERTICAL;

	private World world;
	private Engine engine;
	private SpriteBatch batch;

	private OrthographicCamera camera;
	private Box2DDebugRenderer debugRenderer;

	private final HashMap<TileLocation, Tile> tileMap;

	private CollisionMap collisionMap;

	private Entity cameraFollow;

	public Level() {
		world = new World(new Vector2(0f, 0f), true);
		engine = new Engine();

		camera = new OrthographicCamera(20.0f, 15.0f);
		debugRenderer = new Box2DDebugRenderer();

		tileMap = new HashMap<>();
		collisionMap = new CollisionMap();

		batch = new SpriteBatch();

		engine.addEntityListener(Family.all(PhysicsComponent.class).get(), new PhysicsListener(world));
		engine.addSystem(new PhysicalRenderingSystem(batch));
		engine.addSystem(new PlayerMovementSystem());
	}

	public void update(float deltaTime) {
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		engine.update(deltaTime);
		debugRenderer.render(world, camera.combined);

		world.step(deltaTime, 8, 8);
		batch.end();

		if (cameraFollow != null) {
			Vector3 target = new Vector3(cameraFollow.getComponent(PhysicsComponent.class).body.getPosition(), 0.0f);
			camera.position.lerp(target, 15.0f * deltaTime);
		}
	}

	public Tile getTile(int x, int y) {
		return tileMap.get(new TileLocation(this, x, y));
	}

	public Tile getTile(TileLocation location) {
		return getTile((int) location.x, (int) location.y);
	}

	public void setTile(Tile tile) {
		tileMap.put(tile.getLocation(), tile);
		if (tile.getCollision() != null) {
			collisionMap.update(this, tile);
		}
	}

	public Entity getCameraFollow() {
		return cameraFollow;
	}

	public void setCameraFollow(Entity cameraFollow) {
		this.cameraFollow = cameraFollow;
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

	public Engine getEntityEngine() {
		return engine;
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
