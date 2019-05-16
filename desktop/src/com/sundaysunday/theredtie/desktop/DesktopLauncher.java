package com.sundaysunday.theredtie.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sundaysunday.theredtie.TheRedTie;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 384;
		config.height = 648;

		new LwjglApplication(new TheRedTie(), config);
	}
}
