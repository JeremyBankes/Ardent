package com.jeremy.ardent.entity.system;

import java.util.Comparator;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.jeremy.ardent.entity.component.Mappers;
import com.jeremy.ardent.entity.component.PhysicsComponent;
import com.jeremy.ardent.entity.component.RenderableComponent;

public class PhysicalRenderingSystem extends SortedIteratingSystem {

	private SpriteBatch batch;

	public PhysicalRenderingSystem(SpriteBatch batch) {
		super(Family.all(RenderableComponent.class, PhysicsComponent.class).get(), new Comparator<Entity>() {

			@Override
			public int compare(Entity entityA, Entity entityB) {
				return (int) Math.signum(Mappers.PHYSICS.get(entityA).body.getPosition().y - Mappers.PHYSICS.get(entityB).body.getPosition().y);
			}

		}, 10);
		this.batch = batch;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		Sprite image = Mappers.RENDERABLE.get(entity).sprite;
		Body body = Mappers.PHYSICS.get(entity).body;
		image.setSize(1.0f, 1.0f);
		image.setOrigin(0, 0);
		image.setPosition(body.getPosition().x, body.getPosition().y);
		image.setRotation(body.getAngle() * MathUtils.radDeg);
		image.draw(batch);
	}

}
