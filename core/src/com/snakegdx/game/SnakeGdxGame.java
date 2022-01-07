package com.snakegdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.snakegdx.game.Screens.GameScreen;

public class SnakeGdxGame extends Game {

	private SpriteBatch batch;
	public static int size;
	public static int height;
	public static int width;
	public static GameScreen gameScreen;

	@Override
	public void create() {
		size = 32;
		height = 15;
		width = 20;
		batch = new SpriteBatch();
		gameScreen = new GameScreen(this, width, height, size);
		setScreen(gameScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
