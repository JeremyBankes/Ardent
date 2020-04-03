package com.jeremy.ardent.world.entity.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.jeremy.ardent.world.ClientWorld;

public class PhysicsComponent extends ClientEntityComponent {

	public Body body;
	private BodyDef bodyDef;
	public ClientWorld world;
	public float width = 0.0f;
	public float height = 0.0f;

	public PhysicsComponent(ClientWorld world) {
		this.world = world;

		bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public BodyDef getBodyDef() {
		return bodyDef;
	}

	public ClientWorld getWorld() {
		return world;
	}

}
