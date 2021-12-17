package com.snakegdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SnakeGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	public static  int positionX = 0;
	public static  int positionY = 0;
	int height;
	int width;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("pacmanghost.png");
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		MyInputProcessor ip = new MyInputProcessor();
		Gdx.input.setInputProcessor(ip);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		batch.begin();
		batch.draw(img, positionX, positionY, 128,128);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
