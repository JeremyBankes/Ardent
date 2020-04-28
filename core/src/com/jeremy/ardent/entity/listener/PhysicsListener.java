package com.jeremy.ardent.entity.listener;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jeremy.ardent.entity.component.Mappers;
import com.jeremy.ardent.entity.component.PhysicsComponent;

public class PhysicsListener implements EntityListener {

	private World world;

	public PhysicsListener(World world) {
		this.world = world;
	}

	@Override
	public void entityAdded(Entity entity) {
		PhysicsComponent physics = Mappers.PHYSICS.get(entity);
		physics.body = world.createBody(physics.bodyDefinition);
		for (FixtureDef fixtureDefinition : physics.fixtureDefinitions) {
			physics.body.createFixture(fixtureDefinition);
		}
	}

	@Override
	public void entityRemoved(Entity entity) {
		PhysicsComponent physics = Mappers.PHYSICS.get(entity);
		world.destroyBody(physics.body);
	}

}
