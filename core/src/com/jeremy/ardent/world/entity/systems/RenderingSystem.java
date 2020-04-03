package com.jeremy.ardent.world.entity.systems;

import static java.lang.Math.signum;

import java.util.Comparator;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.jeremy.ardent.world.entity.ClientEntity;
import com.jeremy.ardent.world.entity.components.PhysicsComponent;

public class RenderingSystem extends SortedIteratingSystem {

	private static final Comparator<Entity> Z_COMPARATOR = new Comparator<Entity>() {

		@Override
		public int compare(Entity entityA, Entity entityB) {
			ClientEntity clientEntityA = (ClientEntity) entityA;
			ClientEntity clientEntityB = (ClientEntity) entityB;
			return (int) signum(clientEntityA.getY() - clientEntityB.getY());
		}

	};

	public RenderingSystem() {
		super(Family.all(PhysicsComponent.class).get(), Z_COMPARATOR);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		ClientEntity clientEntity = (ClientEntity) entity;
//		System.out.println(clientEntity.getPosition());
//		System.out.println(clientEntity.getImage().getX());
//		clientEntity.getImage().draw(batch, parentAlpha);
	}

}
