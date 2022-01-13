package com.snakegdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.snakegdx.game.GameController;
import com.snakegdx.game.Models.Stage;
import com.snakegdx.game.MyInputProcessor;
import com.snakegdx.game.SnakeGdxGame;
import com.snakegdx.game.Utilities;

import java.awt.Point;

public class GameScreen extends BaseScreen{

    private final BitmapFont font;
    private final SpriteBatch batch;
    private  final Texture imgSnake, imgFood, imgBlock, imgBody;
    private final Sound soundEat;
    private final Sound soundCollision;

    private final int width;
    private final int height;
    private final int size;

    public static Stage stage;
    public static boolean gameOver;
    public static char previousDirection;
    public static boolean snakeFlag;
    public static boolean pause;
    public static int score;
    public static int level;
    public static int nextLevelScore;
    public static int finalScore;
    public static int lastLevel;
    public  static int velocity;
    public static GameController gameController;
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
        font = new BitmapFont();
        score = 0;
        level = 0;
        nextLevelScore = 50;
        lastLevel = 2;
        finalScore = 0;
        velocity = 200;
        imgSnake = new Texture("snake_head.png");
        imgFood = new Texture("food.png");
        imgBlock = new Texture("block.png");
        imgBody = new Texture("snake_body.png");
        soundEat = Gdx.audio.newSound(Gdx.files.internal("comer.mp3"));
        soundCollision = Gdx.audio.newSound(Gdx.files.internal("choque.mp3"));

        stage = new Stage(width, height, 1);
        gameController = new GameController(stage, width, height, soundEat, soundCollision);
        gameController.start();
        Gdx.graphics.setWindowedMode(size*stage.getWidth(), size*stage.getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        renderGame();

        batch.end();
    }

    private void renderGame(){
            if (!gameOver){
                font.draw(batch, "level: "+(level + 1), 200,50);
                font.draw(batch, "score: "+score,50,50);
                font.draw(batch, "velocity: "+velocity,300,50);
                font.draw(batch, "vidas: "+stage.getSnake().getLife(),400,50);
                int[][] matrixStage = stage.getStage();
                for (int i = 0; i < width; i++){
                    for (int j = 0; j < height ; j++){
                        if(matrixStage[i][j] == Utilities.VALOR_BLOCK){
                            batch.draw(imgBlock, i*size,j*size,size, size);
                        }else if(matrixStage[i][j] == Utilities.VALOR_FOOD){
                            batch.draw(imgFood, i*size,j*size,size, size);
                        }else if(matrixStage[i][j] > 0 && matrixStage[i][j] <= 1000){
                            batch.draw(imgBody, i*size,j*size,size,size);
                        }
                    }
                }
            }else {

                font.draw(batch, "GAME OVER",Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                font.draw(batch, "Puntuacion: "+score,Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2-50);
                font.draw(batch, "Enter para reiniciar", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 -100);
                font.draw(batch, "Esc para Salir", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 -150);

            }
    }

    @Override
    public void dispose() {
        batch.dispose();
        imgSnake.dispose();
        imgBlock.dispose();
        imgFood.dispose();
        imgBody.dispose();
        soundEat.dispose();
        soundCollision.dispose();
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

    private void showMatrix(){
        for (int i = 0; i < stage.getWidth(); i++){
            for (int j = 0; j < stage.getHeight(); j++){
                System.out.print(stage.getStage()[i][j]+"   ");
            }
            System.out.println("");
        }
    }
}