package com.snakegdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.snakegdx.game.Screens.GameScreen;

public class SnakeGdxGame extends Game {

	private SpriteBatch batch;
	private int size;
	private int height;
	private int width;
	private GameScreen gameScreen;

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
