package com.jeremy.ardent.entity.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.jeremy.ardent.entity.component.PhysicsComponent;
import com.jeremy.ardent.entity.component.PlayerMovementComponent;

public class PlayerMovementSystem extends EntitySystem {

	private ImmutableArray<Entity> entities;

	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family.all(PhysicsComponent.class, PlayerMovementComponent.class).get());
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		for (Entity entity : entities) {
			PhysicsComponent physics = (PhysicsComponent) entity.getComponent(PhysicsComponent.class);
			final float acceleration = 80.0f;
			final float maxVelocity = 8.0f;
			final float dashSpeed = 20.0f;
			Vector2 velocity = physics.body.getLinearVelocity();

			float xInput = Gdx.input.isKeyPressed(Keys.A) ? -1 : Gdx.input.isKeyPressed(Keys.D) ? 1 : 0;
			float yInput = Gdx.input.isKeyPressed(Keys.S) ? -1 : Gdx.input.isKeyPressed(Keys.W) ? 1 : 0;
			Vector2 input = new Vector2(xInput, yInput).nor();

			Vector2 force = input.scl(acceleration * physics.body.getMass());

			if (Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT)) {
				force.scl(dashSpeed);
			} else if (velocity.len() > maxVelocity) {
				force.setZero();
			}

			if (!force.isZero() && !physics.body.isAwake()) physics.body.setAwake(true);
			physics.body.applyForceToCenter(force, false);
			physics.body.setLinearDamping(acceleration / 8);
		}
	}

}
