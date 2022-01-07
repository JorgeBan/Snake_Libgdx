package com.snakegdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.snakegdx.game.SnakeGdxGame;

public abstract class BaseScreen implements Screen {
    protected SnakeGdxGame game;
    public BaseScreen(SnakeGdxGame game){
        this.game = game;
    }

}
