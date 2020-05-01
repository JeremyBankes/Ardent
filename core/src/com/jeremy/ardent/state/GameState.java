package com.jeremy.ardent.state;

import com.jeremy.ardent.entity.Player;
import com.jeremy.ardent.level.Level;
import com.jeremy.ardent.level.Tile;

public class GameState extends State {

	private Level level;

	public GameState() {
		level = new Level();
	}

	@Override
	public void onEnter() {
		Player player = new Player(0, 0);
		level.setCameraFollow(player);
		level.addEntity(player);

		level.setTile(new Tile(1, 2));
		level.setTile(new Tile(1, 3));
	}

	@Override
	public void onUpdate(float deltaTime) {
		level.update(deltaTime);
	}

}
