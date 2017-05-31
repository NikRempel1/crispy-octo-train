package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	long time, prev_time;
	float font_pos_x, font_pos_y;
	double delta; 
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);
		img = new Texture("badlogic.jpg");
		prev_time = System.nanoTime();
		font_pos_x = 0;
		font_pos_y = 0;
	}

	@Override
	public void render () {
		time = System.nanoTime();
		delta = (time - prev_time)/1e6;
		prev_time = time;
		gameLoop();
		
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		font.draw(batch, "hello world",font_pos_x, font_pos_y);
		batch.end();
	}
	
	public void gameLoop(){
		font_pos_x += 0.03 * delta;
		font_pos_y += 0.03 * delta;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
