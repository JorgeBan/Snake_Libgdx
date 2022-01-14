package com.snakegdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.snakegdx.game.Controllers.GameController;
import com.snakegdx.game.Controllers.MyInputProcessor;
import com.snakegdx.game.SnakeGdxGame;
import com.snakegdx.game.Utilities;

public class GameScreen extends BaseScreen{

    private final BitmapFont font;
    private final SpriteBatch batch;
    private  final Texture imgSnake, imgFood, imgBlock;
    private final Sound soundEat;
    private final Sound soundCollision;
    private final Sound soundWin;
    private final Sound soundGameOver;
    private final int width;
    private final int height;
    private final int size;

    private GameController gameController;
    private MyInputProcessor myInputProcessor;

    public GameScreen(SnakeGdxGame game, int width, int height, int size) {
        super(game);

        myInputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(myInputProcessor);
        this.width = width;
        this.height = height;
        this.size = size;
        this.batch = game.getBatch();
        this.imgSnake = new Texture("snake_body.png");
        this.imgFood = new Texture("food.png");
        this.imgBlock = new Texture("block.png");

        this.soundEat = Gdx.audio.newSound(Gdx.files.internal("eat.mp3"));
        this.soundCollision = Gdx.audio.newSound(Gdx.files.internal("collision.mp3"));
        this.soundWin = Gdx.audio.newSound(Gdx.files.internal("win.mp3"));
        this.soundGameOver = Gdx.audio.newSound(Gdx.files.internal("game_over.mp3"));
        this.font = new BitmapFont();

        gameController = new GameController(width, height,soundEat, soundCollision, soundGameOver, soundWin);
        gameController.getStage().setGameController(gameController);
        gameController.start();

        myInputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(myInputProcessor);
        myInputProcessor.setGameController(gameController);


        Gdx.graphics.setWindowedMode(size*width, size*height);
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
            if (!gameController.isGameOver()){
                font.draw(batch, "level: "+(gameController.getLevel() + 1), 200,50);
                font.draw(batch, "score: "+gameController.getScore(),50,50);
                font.draw(batch, "speed: "+gameController.getVelocity(),300,50);
                font.draw(batch, "lives: "+ gameController.getStage().getSnake().getLife(),400,50);
                int[][] matrixStage = gameController.getStage().getStage();

                for (int i = 0; i < width; i++){
                    for (int j = 0; j < height ; j++){
                        if(matrixStage[i][j] == Utilities.VALOR_BLOCK){
                            batch.draw(imgBlock, i*size,j*size,size, size);
                        }else if(matrixStage[i][j] == Utilities.VALOR_FOOD){
                            batch.draw(imgFood, i*size,j*size,size, size);
                        }else if(matrixStage[i][j] > 0 && matrixStage[i][j] <= 1000){
                            batch.draw(imgSnake, i*size,j*size,size,size);
                        }
                    }
                }
            }else {

                font.draw(batch, "GAME OVER",Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                font.draw(batch, "Puntuacion: "+gameController.getScore(),Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2-50);
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
        soundEat.dispose();
        soundCollision.dispose();
        soundWin.dispose();
        soundGameOver.dispose();
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