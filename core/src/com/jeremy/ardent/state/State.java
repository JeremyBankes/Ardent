package com.jeremy.ardent.state;

public abstract class State {

	public void onEnter() {};

	public abstract void onUpdate(float deltaTime);

	public void onExit() {};

	public void onResize(int width, int height) {};

	public void dispose() {};

}
