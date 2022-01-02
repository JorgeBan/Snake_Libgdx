package com.snakegdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.snakegdx.game.Models.Snake;
import com.snakegdx.game.Models.Stage;

public class SnakeGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture imgSnake, imgFood, imgBlock, imgYouLose;
	public static int size;
	public static int height;
	public static int width;
	public static Stage stage;
	public static boolean gameOver;
	int score;
	@Override
	public void create () {
		size = 30;
		height = 16;
		width = 16;
		score = 0;
		gameOver = false;
		stage = new Stage(width,height);
		batch = new SpriteBatch();
		imgSnake = new Texture("snake.png");
		imgFood = new Texture("food.png");
		imgBlock = new Texture("block.png");
		imgYouLose = new Texture("you_lose.png");
		Gdx.graphics.setWindowedMode(size*stage.getWidth(), size*stage.getHeight());
		MyInputProcessor ip = new MyInputProcessor();
		Gdx.input.setInputProcessor(ip);

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		if(!gameOver){
			logicGame();
			renderGame();
		}else {
			batch.draw(imgYouLose, 0,0, 512,512);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		imgSnake.dispose();
		imgBlock.dispose();
		imgFood.dispose();
		imgYouLose.dispose();
	}



	private void logicGame(){
		if(stage.getSnake().checkCollision()){
			System.out.println("Choco");
			gameOver = true;
		}

		if (stage.getSnake().checkEatFood(stage.getFood().getPosition())){
			score += 10;
			stage.createFood();
			stage.getSnake().increaseSnakeLength();
			stage.Update();
		}
	}


	private void renderGame(){

		int[][] matrixStage = stage.getStage();
		for (int i = 0; i < width ; i++){
			for (int j = 0; j < height ; j++){
				if(matrixStage[i][j] == 1002){
					batch.draw(imgBlock, i*size,j*size,size, size);
				}else if(matrixStage[i][j] == 1001){
					batch.draw(imgFood, i*size,j*size,size, size);
				}else if(matrixStage[i][j] > 0 && matrixStage[i][j] <= 1000){
					batch.draw(imgSnake, i*size,j*size,size, size);
				}
			}
		}
		//stage.Update();
	}
}
