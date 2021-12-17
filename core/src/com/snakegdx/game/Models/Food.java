package com.snakegdx.game.Models;

import java.awt.Point;
import java.util.Random;

public class Food {

    private Point position;
    private Random random;
    private Point limit;

    public Food(Point limit){
        this.position = new Point();
        this.limit = limit;
        this.random = new Random();
    }

    public void generateFood(){
        this.position.x = random.nextInt(this.limit.x) + 1;
        this.position.y = random.nextInt(this.limit.y) + 1;
    }

    public Point getFood(){
        return this.position;
    }
}
