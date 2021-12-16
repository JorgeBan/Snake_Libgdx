package com.snakegdx.game.Models;

import java.awt.Point;

public class Stage {
    private Snake snake;
    int width;
    int height;
    Point food;

    public Stage(int length, int high) {
        super();
        this.width = length;
        this.height = high;
        initSnake();
        food = createFood();
    }

    private void initSnake() {
        snake = new Snake();
        int x = width/2;
        int y = height/2;
        for(int i = 0;i<5;i++) {
            snake.addTail(new Point(x, y));
        }
    }

    private Point createFood(){
        return new Point(0,0);
    }

    public Point getFood() {
        return food;
    }

    public Snake getSnake() {
        return snake;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
