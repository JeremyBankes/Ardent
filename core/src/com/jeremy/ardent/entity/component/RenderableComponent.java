package com.jeremy.ardent.entity.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RenderableComponent implements Component {

	public Sprite sprite;

	public RenderableComponent(Texture texture) {
		this.sprite = new Sprite(texture);
	}

}
