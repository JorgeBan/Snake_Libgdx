package com.snakegdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.snakegdx.game.Models.Snake;
import com.snakegdx.game.Models.Stage;

public class SnakeGdxGame extends ApplicationAdapter {
	BitmapFont font;
	SpriteBatch batch;
	Texture imgSnake, imgFood, imgBlock, imgYouLose;
	public static int size;
	public static int height;
	public static int width;
	public static Stage stage;
	public static boolean gameOver;
	public static char previousDirection;
	public static boolean snakeFlag;
	public static boolean pause;
	int score;
	@Override
	public void create () {
		size = 32;
		height = 15;
		width = 20;
		score = 0;
		font = new BitmapFont();
		previousDirection = 'R';
		snakeFlag = false;
		gameOver = false;
		pause = false;
		stage = new Stage(width,height);
		batch = new SpriteBatch();
		imgSnake = new Texture("snake.png");
		imgFood = new Texture("food.png");
		imgBlock = new Texture("block.png");
		imgYouLose = new Texture("you_lose.png");
		Gdx.graphics.setWindowedMode(size*width, size*height);
		MyInputProcessor ip = new MyInputProcessor();
		Gdx.input.setInputProcessor(ip);
		System.out.println("W: "+Gdx.graphics.getWidth() +" H: "+Gdx.graphics.getHeight());
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		if(!gameOver){
			try{
				Thread.sleep(200);
				logicGame();
				renderGame();
			}catch (InterruptedException e){
				System.out.println(e.toString());
			}
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
		if(!pause){
			font.draw(batch, "score: "+score,50,50);
			if(stage.getSnake().checkCollision()){
				System.out.println("Collision");
				gameOver = true;
			}else {
				if (stage.getSnake().checkEatFood(stage.getFood().getPosition())){
					score += 10;
					stage.createFood();
					stage.getSnake().increaseSnakeLength();
				}

				if(!snakeFlag){
					stage.getSnake().changeDirection(previousDirection);
					stage.getSnake().move();
				}
				stage.Update();
				stage.showMatrix();
				snakeFlag = false;
			}
		}

	}


	private void renderGame(){

		int[][] matrixStage = stage.getStage();
		for (int i = 0; i < width ; i++){
			for (int j = 0; j < height ; j++){
				if(matrixStage[i][j] == Stage.VALOR_BLOCK){
					batch.draw(imgBlock, i*size,j*size,size, size);
					//System.out.println("matrix["+i+"]"+"["+j+"]");
				}else if(matrixStage[i][j] == Stage.VALOR_FOOD){
					batch.draw(imgFood, i*size,j*size,size, size);
				}else if(matrixStage[i][j] > 0 && matrixStage[i][j] <= 1000){
					batch.draw(imgSnake, i*size,j*size,size, size);
				}
			}
		}
	}
}
