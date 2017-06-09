package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.PlayScreen;


public class MyGdxGame extends Game {
	//Temporary values
	public static final int V_WIDTH = 256, V_HEIGHT = 256;
	public static final float PPM = 100;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
