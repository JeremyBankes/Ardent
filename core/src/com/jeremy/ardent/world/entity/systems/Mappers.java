package com.jeremy.ardent.world.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.jeremy.ardent.world.entity.components.PhysicsComponent;

public class Mappers {

	public static final ComponentMapper<PhysicsComponent> PHYSICS = ComponentMapper.getFor(PhysicsComponent.class);

}
