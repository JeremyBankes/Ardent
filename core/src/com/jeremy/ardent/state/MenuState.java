package com.jeremy.ardent.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MenuState extends State {

	private Stage stage;
	private Table table;
	private Stack pages;

	public MenuState() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		table = new Table();
		table.setFillParent(true);
		table.setDebug(true);

		pages = new Stack();

		//		pages.addActor(new Image(new Texture(pixmap)));

		table.add(pages).expand().fill();

		stage.addActor(table);
	}

	@Override
	public void onUpdate(float deltaTime) {
		stage.act(deltaTime);
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
