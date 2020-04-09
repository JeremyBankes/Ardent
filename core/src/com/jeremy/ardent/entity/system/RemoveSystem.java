package com.jeremy.ardent.entity.system;

import com.badlogic.ashley.core.EntitySystem;
import com.jeremy.ardent.Core;

public class RemoveSystem extends EntitySystem {

	private Core core;

	public RemoveSystem(Core core) {
		this.core = core;
	}

}
