package com.jeremy.ardent.state;

import java.util.HashMap;

public class StateManager {

	public static final HashMap<Class<? extends State>, State> stateMap = new HashMap<>();

	private static State previousState;
	private static State currentState;

	public static void registerState(State state) {
		stateMap.put(state.getClass(), state);
	}

	public static <T extends State> T getState(Class<T> stateClass) {
		return stateClass.cast(stateMap.get(stateClass));
	}

	public static State changeState(Class<? extends State> stateClass) {
		if (currentState != null) {
			previousState = currentState;
			currentState.onExit();
		}
		currentState = getState(stateClass);
		currentState.onEnter();
		return currentState;
	}

	public static boolean hasCurrentState() {
		return currentState != null;
	}

	public static State getCurrentState() {
		return currentState;
	}

	public static State getPreviousState() {
		return previousState;
	}

}
