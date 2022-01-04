package com.snakegdx.game.Models;

import com.snakegdx.game.SnakeGdxGame;

import java.awt.Point;
import java.util.Random;

public class Food {

    private final Point position;
    private final Random random;

    public Food(){
        this.position = new Point();
        this.random = new Random();
    }

    public void generateFood(int limitX, int limitY){
        this.position.x = random.nextInt(limitX - 1);
        this.position.y = random.nextInt(limitY - 1);
    }

    public Point getPosition(){
        return this.position;
    }
}
