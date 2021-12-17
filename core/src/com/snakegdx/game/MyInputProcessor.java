package com.snakegdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {


    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.D){
            SnakeGdxGame.positionX += 10;
        }
        if(keycode == Input.Keys.A){
            SnakeGdxGame.positionX -= 10;
        }
        if(keycode == Input.Keys.W){
            SnakeGdxGame.positionY += 10;
        }
        if(keycode == Input.Keys.S){
            SnakeGdxGame.positionY -= 10;
        }
        return true;
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
