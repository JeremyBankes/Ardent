package com.jeremy.ardent;

import com.badlogic.gdx.Gdx;
import com.jeremy.ardent.input.InputHandler;
import com.jeremy.ardent.level.Level;

public class Core {

	private Level level;

	private InputHandler inputHandler;

	public Core() {
		level = new Level();

		inputHandler = new InputHandler();

		Gdx.input.setInputProcessor(inputHandler);
	}

	void update(float deltaTime) {
		level.update(deltaTime);
	}

	public void dispose() {
		level.dispose();
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}

	public void resize(int width, int height) {
		level.resize(width, height);
	}

}
