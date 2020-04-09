package com.jeremy.ardent.input;

public class KeyEvent {

	private KeyEventType type;
	private int keyCode;

	public KeyEvent(KeyEventType type, int keyCode) {
		this.type = type;
		this.keyCode = keyCode;
	}

	public KeyEventType getType() {
		return type;
	}

	public int getKeyCode() {
		return keyCode;
	}

}
