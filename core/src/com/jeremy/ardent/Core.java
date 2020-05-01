package com.jeremy.ardent;

import static com.jeremy.ardent.state.StateManager.changeState;
import static com.jeremy.ardent.state.StateManager.getCurrentState;
import static com.jeremy.ardent.state.StateManager.hasCurrentState;
import static com.jeremy.ardent.state.StateManager.registerState;

import com.badlogic.gdx.Gdx;
import com.jeremy.ardent.input.InputHandler;
import com.jeremy.ardent.state.GameState;
import com.jeremy.ardent.state.MenuState;

public class Core {

	private InputHandler inputHandler;

	public Core() {
		inputHandler = new InputHandler();

		registerState(new MenuState());
		registerState(new GameState());
		changeState(GameState.class);

		Gdx.input.setInputProcessor(inputHandler);
	}

	void update(float deltaTime) {
		if (hasCurrentState()) getCurrentState().onUpdate(deltaTime);
	}

	public void dispose() {
		if (hasCurrentState()) getCurrentState().dispose();
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}

	public void onResize(int width, int height) {
		if (hasCurrentState()) getCurrentState().onResize(width, height);
	}

}
