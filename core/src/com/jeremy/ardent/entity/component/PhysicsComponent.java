package com.jeremy.ardent.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class PhysicsComponent implements Component {

	public BodyDef bodyDefinition;
	public FixtureDef[] fixtureDefinitions;
	public Body body;

	public PhysicsComponent(BodyDef bodyDefinition, FixtureDef... fixtureDefinitions) {
		this.bodyDefinition = bodyDefinition;
		this.fixtureDefinitions = fixtureDefinitions;
	}

}
