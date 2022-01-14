package com.snakegdx.game.Controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.snakegdx.game.Models.Stage;
import com.snakegdx.game.Screens.GameScreen;
import com.snakegdx.game.Utilities;

public class GameController extends Thread{

    private  Stage stage;
    private Long time;
    private final  Sound soundEat;
    private final Sound soundCollision;
    private final  Sound soundWin;
    private final Sound soundGameOver;



    private  boolean gameOver;


    private char previousDirection;
    private boolean snakeFlag;
    private boolean pause;
    private int score;
    private int level;
    private int nextLevelScore;
    private final int lastLevel;
    private int speed;


    public GameController(int width, int height, Sound soundEat, Sound soundCollision, Sound soundGameOver, Sound soundWin){
        this.stage = new Stage(width, height, 0);
        this.time = 0L;
        this.soundEat = soundEat;
        this.soundCollision = soundCollision;
        this.soundWin = soundWin;
        this.soundGameOver = soundGameOver;

        this.gameOver = false;
        this.previousDirection = 'R';
        this.snakeFlag = false;
        this.pause = false;
        this.score = 0;
        this.level = 0;
        this.nextLevelScore = Utilities.NEXT_LEVEL_SCORE;
        this.lastLevel = 2;
        this.speed = Utilities.SPEED;
    }

    public void run(){

        while (true){
            if(!pause && !gameOver){
                if(score > nextLevelScore){
                   if(level < lastLevel){
                       soundWin.play();
                       level += 1;
                       speed -=Utilities.REDUCE_SPEED;
                       nextLevelScore = nextLevelScore + Utilities.NEXT_LEVEL_SCORE;
                   }

                }
                if(System.currentTimeMillis() - time > speed){
                        if (stage.getSnake().checkEatFood(stage.getFood().getPosition())){
                            soundEat.play();
                            score += Utilities.ADD_LEVEL_SCORE;
                            stage.createFood(level);
                            stage.getSnake().increaseSnakeLength();
                        }

                        if(!snakeFlag){
                            stage.getSnake().changeDirection(previousDirection);
                            stage.getSnake().move();
                        }

                        stage.Update(level);
                        snakeFlag = false;
                    time = System.currentTimeMillis();
                }
            }


        }
    }


    public Stage getStage() {
        return stage;
    }

    public Sound getSoundCollision() {
        return soundCollision;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Sound getSoundGameOver() {
        return soundGameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public char getPreviousDirection() {
        return previousDirection;
    }

    public void setPreviousDirection(char previousDirection){
        this.previousDirection = previousDirection;
    }

    public void setSnakeFlag(boolean snakeFlag){
        this.snakeFlag = snakeFlag;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause){
        this.pause = pause;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setNextLevelScore(int nextLevelScore){
        this.nextLevelScore = nextLevelScore;
    }

    public int getVelocity() {
        return speed;
    }

    public void setVelocity(int velocity){
        this.speed = velocity;
    }
}
