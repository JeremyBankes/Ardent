package com.jeremy.ardent.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jeremy.ardent.Ardent;

public class DesktopLauncher {

	private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();

	public static void main(String[] arg) {
		Dimension screenSize = TOOLKIT.getScreenSize();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.x = (screenSize.width - config.width) / 2;
		config.y = (screenSize.height - config.height) / 2;

		new LwjglApplication(new Ardent(), config);
	}
}
