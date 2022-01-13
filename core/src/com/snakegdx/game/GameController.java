package com.snakegdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Sound;
import com.snakegdx.game.Models.Stage;
import com.snakegdx.game.Screens.GameScreen;

public class GameController extends Thread{

    private  Stage stage;
    private final int width;
    private final int height;
    private Long time;
    private final  Sound soundEat;
    private final Sound soundCollision;
    public GameController(Stage stage, int width, int height, Sound soundEat, Sound soundCollision){
        this.stage = stage;
        this.width = width;
        this.height = height;
        this.time = 0L;
        this.soundEat = soundEat;
        this.soundCollision = soundCollision;
    }

    public void run(){

        while (true){

            if(!GameScreen.pause && !GameScreen.gameOver){
                if(GameScreen.score > GameScreen.nextLevelScore){
                   if(GameScreen.level < GameScreen.lastLevel){
                       GameScreen.level += 1;
                       GameScreen.velocity -=30;
                       GameScreen.nextLevelScore = GameScreen.nextLevelScore + 50;
                       GameScreen.previousDirection = 'R';
                       soundCollision.play();
                   }

                }
                if(System.currentTimeMillis() - time > GameScreen.velocity){
                        if (stage.getSnake().checkEatFood(stage.getFood().getPosition())){
                            soundEat.play();
                            GameScreen.score += 10;
                            stage.createFood(GameScreen.level);
                            stage.getSnake().increaseSnakeLength();
                        }

                        if(!GameScreen.snakeFlag){
                            stage.getSnake().changeDirection(GameScreen.previousDirection);
                            stage.getSnake().move();
                        }
                        stage.Update(GameScreen.level);
                        GameScreen.snakeFlag = false;
                    time = System.currentTimeMillis();
                }
            }


        }
    }
}
