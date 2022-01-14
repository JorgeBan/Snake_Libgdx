package com.snakegdx.game.Controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.snakegdx.game.Utilities;

public class MyInputProcessor extends InputAdapter {


    private GameController gameController;

    @Override
    public boolean keyDown(int keycode) {
        if(!gameController.isGameOver()){
            switch (keycode){
                case Input.Keys.SPACE:
                    gameController.setPause(!gameController.isPause());;
                    break;

                case Input.Keys.W:
                    if(gameController.getPreviousDirection() != 'D'){
                        gameController.getStage().getSnake().changeDirection('U');
                        gameController.setPreviousDirection('U');
                        gameController.getStage().getSnake().move();
                    }
                    break;

                case Input.Keys.S:
                    if (gameController.getPreviousDirection() != 'U'){
                        gameController.getStage().getSnake().changeDirection('D');
                        gameController.setPreviousDirection('D');
                        gameController.getStage().getSnake().move();
                    }
                    break;

                case Input.Keys.D:
                    if(gameController.getPreviousDirection() != 'L'){
                        gameController.getStage().getSnake().changeDirection('R');
                        gameController.setPreviousDirection('R');
                        gameController.getStage().getSnake().move();                    }
                    break;

                case Input.Keys.A:
                    if(gameController.getPreviousDirection() != 'R'){
                        gameController.getStage().getSnake().changeDirection('L');
                        gameController.setPreviousDirection('L');
                        gameController.getStage().getSnake().move();
                    }
                    break;

            }
            gameController.setSnakeFlag(true);

        }else {
            switch (keycode){
                case Input.Keys.ENTER:
                    gameController.setVelocity(Utilities.SPEED);
                    gameController.setLevel(0);
                    gameController.setScore(0);
                    gameController.setNextLevelScore(Utilities.NEXT_LEVEL_SCORE);
                    gameController.setPreviousDirection('R');
                    gameController.getStage().init(gameController.getLevel());
                    gameController.setGameOver(false);

                    break;

                case Input.Keys.ESCAPE:
                    System.exit(0);
                    break;
            }
        }

        return true;
    }

    public void setGameController(GameController gameController){
        this.gameController = gameController;
    }
}