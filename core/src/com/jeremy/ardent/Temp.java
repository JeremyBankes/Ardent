package com.jeremy.ardent;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Temp extends ApplicationAdapter {

	private World world;
	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;

	@Override
	public void create() {
		world = new World(new Vector2(0, 0), true);
		camera = new OrthographicCamera(20.0f, 15.0f);
		renderer = new Box2DDebugRenderer();

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;

		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(0.5f, 0.5f);
		fixtureDef.shape = polygonShape;

		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);

		body.setLinearVelocity(1.0f, 0.0f);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.step(Gdx.graphics.getDeltaTime(), 4, 4);
		renderer.render(world, camera.combined);
	}

	@Override
	public void dispose() {
		world.dispose();
	}

}
