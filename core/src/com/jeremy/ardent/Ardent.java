package com.jeremy.ardent;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Ardent extends ApplicationAdapter {

	private Core core;

	@Override
	public void create() {
		core = new Core();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		core.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		core.dispose();
	}

	@Override
	public void resize(int width, int height) {
		core.resize(width, height);
	}

}
