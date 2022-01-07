package com.snakegdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.snakegdx.game.Models.Stage;
import com.snakegdx.game.MyInputProcessor;
import com.snakegdx.game.SnakeGdxGame;

public class GameScreen extends BaseScreen{

    private final BitmapFont font;
    private final SpriteBatch batch;
    private  final Texture imgSnake, imgFood, imgBlock;
    private int score;
    private int level;
    private int velocity;

    private final int width;
    private final int height;
    private final int size;

    public static Stage stage;
    public static boolean gameOver;
    public static char previousDirection;
    public static boolean snakeFlag;
    public static boolean pause;



    public GameScreen(SnakeGdxGame game, int width, int height, int size) {
        super(game);

        MyInputProcessor myInputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(myInputProcessor);
        this.width = width;
        this.height = height;
        this.size = size;
        this.batch = game.getBatch();
        gameOver = false;
        previousDirection = 'R';
        snakeFlag = false;
        pause = false;
        level = 1;
        font = new BitmapFont();
        score = 0;
        level = 1;
        velocity = 200;
        imgSnake = new Texture("snake.png");
        imgFood = new Texture("food.png");
        imgBlock = new Texture("block.png");


        stage = new Stage(width, height, 1);

        Gdx.graphics.setWindowedMode(size*stage.getWidth(), size*stage.getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
            try{
                Thread.sleep(velocity);
                logicGame();
                renderGame();
            }catch (InterruptedException e){
                System.out.println(e.toString());
            }

        batch.end();
    }

    private void logicGame(){
        if(score >= 50){
            level = 2;
        }
        if(!pause){
            if(stage.getSnake().checkCollision(width, height, stage.getStage(), Stage.VALOR_BLOCK)){
                gameOver = true;
            }else {
                if (stage.getSnake().checkEatFood(stage.getFood().getPosition())){
                    if(level == 2){
                        velocity -=20;
                    }
                    score += 10;
                    stage.createFood(level);
                    stage.getSnake().increaseSnakeLength();
                }

                if(!snakeFlag){
                    stage.getSnake().changeDirection(previousDirection);
                    stage.getSnake().move();
                }
                stage.Update(level);
                snakeFlag = false;
            }
        }

    }


    private void renderGame(){
        if (!gameOver){
            font.draw(batch, "level: "+level, 200,50);
            font.draw(batch, "score: "+score,50,50);
            int[][] matrixStage = stage.getStage();
            for (int i = 0; i < width; i++){
                for (int j = 0; j < height ; j++){
                    if(matrixStage[i][j] == Stage.VALOR_BLOCK){
                        batch.draw(imgBlock, i*size,j*size,size, size);
                    }else if(matrixStage[i][j] == Stage.VALOR_FOOD){
                        batch.draw(imgFood, i*size,j*size,size, size);
                    }else if(matrixStage[i][j] > 0 && matrixStage[i][j] <= 1000){
                        batch.draw(imgSnake, i*size,j*size,size,size);
                    }
                }
            }
        }else {
            level = 1;
            score = 0;
            velocity = 200;
            stage.init(level);
            font.draw(batch, "GAME OVER",Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
            font.draw(batch, "Enter para reiniciar", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 -50);
            font.draw(batch, "Esc para Salir", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 -100);

        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        imgSnake.dispose();
        imgBlock.dispose();
        imgFood.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
