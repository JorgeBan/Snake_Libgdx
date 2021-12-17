package com.snakegdx.game.Models;

import java.awt.Point;

public class Stage {
    private Snake snake;
    private int width;
    private int height;
    private Food food;

    public Stage(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Point(width/2, height/2));
        this.food = new Food(new Point(width, height));
    }

    public void createFood(){
        this.food.generateFood();
    }

    public Food getFood() {
        return food;
    }

    public Snake getSnake() {
        return snake;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
