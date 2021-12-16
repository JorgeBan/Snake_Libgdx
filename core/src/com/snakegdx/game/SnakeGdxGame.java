package com.snakegdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SnakeGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	int positionX = 0;
	int positionY = 0;
	int height;
	int width;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("pacmanghost.png");
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		if(Gdx.input.isTouched()){
			if(Gdx.input.getX() >= width/2){
				this.positionX = this.positionX + 2;
			}else {
				this.positionX = this.positionX - 2;
			}

			if(Gdx.input.getY() >= height/2){
				this.positionY = this.positionY + 2;
			}else {
				this.positionY = this.positionY - 2;
			}

		}

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
