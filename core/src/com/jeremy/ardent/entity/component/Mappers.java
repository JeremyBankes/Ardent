package com.jeremy.ardent.entity.component;

import com.badlogic.ashley.core.ComponentMapper;

public class Mappers {

	public static final ComponentMapper<PhysicsComponent> PHYSICS = ComponentMapper.getFor(PhysicsComponent.class);
	public static final ComponentMapper<RenderableComponent> RENDERABLE = ComponentMapper.getFor(RenderableComponent.class);

}
