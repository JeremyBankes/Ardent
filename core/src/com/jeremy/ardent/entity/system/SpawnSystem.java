package com.jeremy.ardent.entity.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.jeremy.ardent.entity.component.PhysicsComponent;

public class SpawnSystem extends EntitySystem {

	private ImmutableArray<Entity> entities;
	private int lastSize;

	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class).get());
		System.out.println("Added af");
	}

	@Override
	public void update(float deltaTime) {
		if (lastSize != entities.size()) {
			lastSize = entities.size();
			System.out.println(lastSize);
		}
	}
}
