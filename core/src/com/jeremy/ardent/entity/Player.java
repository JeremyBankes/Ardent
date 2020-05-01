package com.jeremy.ardent.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.jeremy.ardent.entity.component.PhysicsComponent;
import com.jeremy.ardent.entity.component.PlayerMovementComponent;

public class Player extends Entity {

	public Player(float x, float y) {
		super();

		BodyDef bodyDefinition = new BodyDef();
		bodyDefinition.type = BodyType.DynamicBody;
		bodyDefinition.position.set(x, y);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(0.5f, 0.5f);
		FixtureDef fixtureDefinition = new FixtureDef();
		fixtureDefinition.shape = shape;
		fixtureDefinition.density = 30.0f;

		add(new PhysicsComponent(bodyDefinition, fixtureDefinition));
		add(new PlayerMovementComponent());
	}

}
