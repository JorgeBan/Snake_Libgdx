package com.snakegdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {


    @Override
    public boolean keyDown(int keycode) {
        if(!SnakeGdxGame.gameOver){
            switch (keycode){
                case Input.Keys.W:
                    SnakeGdxGame.stage.getSnake().changeDirection('U');
                    break;

                case Input.Keys.S:
                    SnakeGdxGame.stage.getSnake().changeDirection('D');
                    break;

                case Input.Keys.D:
                    SnakeGdxGame.stage.getSnake().changeDirection('R');
                    break;

                case Input.Keys.A:
                    SnakeGdxGame.stage.getSnake().changeDirection('L');
                    break;

            }

            SnakeGdxGame.stage.getSnake().move();
            SnakeGdxGame.stage.Update();
            SnakeGdxGame.stage.showMatrix();
            //showBody();

        }

        return true;
    }

    private void showBody(){
        System.out.println("tamaño de la serpiente: "+SnakeGdxGame.stage.getSnake().getBody().size());
        for (int i = 0; i < SnakeGdxGame.stage.getSnake().getBody().size(); i++){
            System.out.println("puntoX "+i+"  "+SnakeGdxGame.stage.getSnake().getBody().get(i).x);
            System.out.println("puntoY "+i+"  "+SnakeGdxGame.stage.getSnake().getBody().get(i).y);
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
       public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
