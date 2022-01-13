package com.snakegdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.snakegdx.game.Screens.GameScreen;

public class MyInputProcessor extends InputAdapter {


    @Override
    public boolean keyDown(int keycode) {
        if(!GameScreen.gameOver){
            switch (keycode){
                case Input.Keys.SPACE:
                    GameScreen.pause = !GameScreen.pause;
                    break;

                case Input.Keys.W:
                    if(GameScreen.previousDirection != 'D'){
                        GameScreen.stage.getSnake().changeDirection('U');
                        GameScreen.previousDirection = 'U';
                        GameScreen.stage.getSnake().move();
                    }
                    break;

                case Input.Keys.S:
                    if (GameScreen.previousDirection != 'U'){
                        GameScreen.stage.getSnake().changeDirection('D');
                        GameScreen.previousDirection = 'D';
                        GameScreen.stage.getSnake().move();
                    }
                    break;

                case Input.Keys.D:
                    if(GameScreen.previousDirection != 'L'){
                        GameScreen.stage.getSnake().changeDirection('R');
                        GameScreen.previousDirection = 'R';
                        GameScreen.stage.getSnake().move();
                    }
                    break;

                case Input.Keys.A:
                    if(GameScreen.previousDirection != 'R'){
                        GameScreen.stage.getSnake().changeDirection('L');
                        GameScreen.previousDirection = 'L';
                        GameScreen.stage.getSnake().move();
                    }
                    break;

            }

            System.out.println(keycode);
            GameScreen.snakeFlag = true;

        }else {
            switch (keycode){
                case Input.Keys.ENTER:
                    GameScreen.velocity = 200;
                    GameScreen.level = 0;
                    GameScreen.score = 0;
                    GameScreen.nextLevelScore = 50;
                    GameScreen.previousDirection = 'R';
                    GameScreen.stage.init(GameScreen.level);
                    GameScreen.gameOver = false;

                    break;

                case Input.Keys.ESCAPE:
                    System.exit(0);
                    break;
            }
        }

        return true;
    }
}