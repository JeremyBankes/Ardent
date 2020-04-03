package com.jeremy.ardent;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.jeremy.ardent.world.ClientWorld;
import com.jeremy.ardent.world.entity.Player;

public class Ardent extends Game {

	private ClientWorld world;

	@Override
	public void create() {
		world = new ClientWorld();

		Player player = new Player(world, 0f, 0f);
		player.spawn();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0.25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		world.resize(width, height);
	}
}
