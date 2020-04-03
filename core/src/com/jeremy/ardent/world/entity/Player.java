package com.jeremy.ardent.world.entity;

import com.jeremy.ardent.world.ClientWorld;

public class Player extends Creature {

	public Player(ClientWorld world, float x, float y) {
		super(world, x, y, EntityType.PLAYER);
	}

}
