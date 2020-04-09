package com.jeremy.ardent.input;

import java.util.LinkedHashSet;

import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {

	private final LinkedHashSet<KeyListener> keyListeners;

	public InputHandler() {
		keyListeners = new LinkedHashSet<>();
	}

	public boolean add(KeyListener listener) {
		return keyListeners.add(listener);
	}

	public boolean remove(Object listener) {
		return keyListeners.remove(listener);
	}

	@Override
	public boolean keyDown(int keyCode) {
		KeyEvent event = new KeyEvent(KeyEventType.PRESS, keyCode);
		for (KeyListener keyListener : keyListeners) if (keyListener.fire(event)) return true;
		return false;
	}

	@Override
	public boolean keyUp(int keyCode) {
		KeyEvent event = new KeyEvent(KeyEventType.RELEASE, keyCode);
		for (KeyListener keyListener : keyListeners) if (keyListener.fire(event)) return true;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		KeyEvent event = new KeyEvent(KeyEventType.TYPE, character);
		for (KeyListener keyListener : keyListeners) if (keyListener.fire(event)) return true;
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
