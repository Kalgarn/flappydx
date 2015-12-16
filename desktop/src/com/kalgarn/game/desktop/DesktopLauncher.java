package com.kalgarn.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kalgarn.game.FlappyGDX;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyGDX.WIDTH;
		config.height = FlappyGDX.HEIGHT;
		config.title = FlappyGDX.TITLE;
		new LwjglApplication(new FlappyGDX(), config);
	}
}
