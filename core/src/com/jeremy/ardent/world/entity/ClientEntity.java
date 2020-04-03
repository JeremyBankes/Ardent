package com.jeremy.ardent.world.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jeremy.ardent.world.ClientWorld;
import com.jeremy.ardent.world.entity.components.PhysicsComponent;
import com.jeremy.ardent.world.entity.systems.Mappers;

public abstract class ClientEntity extends Entity {

	private Image image;

	public ClientEntity(ClientWorld world, float x, float y, EntityType entityType) {
		this.add(new PhysicsComponent(world));
		image = new Image(entityType.getTexture());
		setSize(entityType.getWidth(), entityType.getHeight());
		setPosition(x, y);
	}

	public PhysicsComponent getPhysics() {
		return Mappers.PHYSICS.get(this);
	}

	private Body getBody() {
		return getPhysics().body;
	}

	public float getWidth() {
		return getPhysics().width;
	}

	public float getHeight() {
		return getPhysics().height;
	}

	public void setWidth(float width) {
		getPhysics().width = width;
		image.setWidth(width);
	}

	public void setHeight(float height) {
		getPhysics().height = height;
		image.setHeight(height);
	}

	public void setSize(float width, float height) {
		setWidth(width);
		setHeight(height);
	}

	public Vector2 getPosition() {
		if (getBody() == null) return getPhysics().getBodyDef().position;
		return getBody().getPosition();
	}

	public void setPosition(float x, float y) {
		setX(x);
		setY(y);
	}

	public float getX() {
		return getPosition().x;
	}

	public void setX(float x) {
		getPosition().x = x;
		image.setX(x - getWidth() / 2);
	}

	public float getY() {
		return getPosition().y;
	}

	public void setY(float y) {
		getPosition().y = y;
		image.setY(y - getHeight() / 2);
	}

	public ClientWorld getWorld() {
		return getPhysics().world;
	}

	public Image getImage() {
		return image;
	}

	private void createBody() {
		getPhysics().setBody(getWorld().getPhysicsWorld().createBody(getPhysics().getBodyDef()));
		PolygonShape bounds = new PolygonShape();
		bounds.setAsBox(getWidth() / 2, getHeight() / 2);
		getBody().createFixture(bounds, 1.0f);
		bounds.dispose();
	}

	private void destroyBody() {
		getWorld().getPhysicsWorld().destroyBody(getBody());
	}

	public void spawn() {
		createBody();
		getWorld().getEntityEngine().addEntity(this);
		getWorld().getStage().addActor(image);
	}

	public void remove() {
		image.remove();
		getWorld().getEntityEngine().removeEntity(this);
		destroyBody();
	}

}
