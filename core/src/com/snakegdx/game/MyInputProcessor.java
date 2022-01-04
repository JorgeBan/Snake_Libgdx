package com.snakegdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor extends InputAdapter {


    @Override
    public boolean keyDown(int keycode) {
        if(!SnakeGdxGame.gameOver){
            switch (keycode){
                case Input.Keys.SPACE:
                    SnakeGdxGame.pause = !SnakeGdxGame.pause;
                    break;

                case Input.Keys.W:
                    if(SnakeGdxGame.previousDirection != 'D'){
                        SnakeGdxGame.stage.getSnake().changeDirection('U');
                        SnakeGdxGame.previousDirection = 'U';
                        SnakeGdxGame.stage.getSnake().move();
                    }
                    break;

                case Input.Keys.S:
                    if (SnakeGdxGame.previousDirection != 'U'){
                        SnakeGdxGame.stage.getSnake().changeDirection('D');
                        SnakeGdxGame.previousDirection = 'D';
                        SnakeGdxGame.stage.getSnake().move();
                    }
                    break;

                case Input.Keys.D:
                        if(SnakeGdxGame.previousDirection != 'L'){
                            SnakeGdxGame.stage.getSnake().changeDirection('R');
                            SnakeGdxGame.previousDirection = 'R';
                            SnakeGdxGame.stage.getSnake().move();
                        }
                    break;

                case Input.Keys.A:
                    if(SnakeGdxGame.previousDirection != 'R'){
                        SnakeGdxGame.stage.getSnake().changeDirection('L');
                        SnakeGdxGame.previousDirection = 'L';
                        SnakeGdxGame.stage.getSnake().move();
                    }
                    break;

            }

            SnakeGdxGame.snakeFlag = true;
            SnakeGdxGame.stage.showMatrix();

        }

        return true;
    }
}
