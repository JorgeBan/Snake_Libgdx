package com.snakegdx.game.Models;

import com.snakegdx.game.SnakeGdxGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Stage {
    public static final int VALOR_FOOD = 1001;
    public static final int VALOR_BLOCK = 1002;
    private  Snake snake;
    private final int width;
    private final int height;
    private final Food food;
    private final int[][] stage;

    public Stage(int width, int height, int level) {
        this.width = width;
        this.height = height;
        this.food = new Food();
        this.stage = new int[width][height];
        init(level);
    }

    public void init(int level){
        this.snake = new Snake(new Point(width/2, height/2));
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                this.stage[i][j] = 0;
            }
        }

        ArrayList<Point> bodySnake = snake.getBody();
        for(int i = 0; i < bodySnake.size(); i++){
            this.stage[bodySnake.get(i).x][bodySnake.get(i).y] = i + 1;
        }

        if (level == 1) {
            createBlock();
        }else {
            createBlockLevel2();
        }
        createFood(level);


    }

    private void createBlock() {
        for (int i = 0; i < width; i++){
            stage[i][0] = VALOR_BLOCK;
            stage[i][height-1] = VALOR_BLOCK;
        }

        for (int j = 0; j < height; j++){
            stage[0][j] = VALOR_BLOCK;
            stage[width-1][j] = VALOR_BLOCK;
        }
    }

    public int[][] getStage() {
        return stage;
    }


    public void createFood(int level){
        this.food.generateFood(width, height);
        int foodX = this.food.getPosition().x;
        int foodY = this.food.getPosition().y;
        if(this.stage[foodX][foodY] == 0){
            this.stage[foodX][foodY] = VALOR_FOOD;
        }else{
            createFood(level);
        }
        Update(level);
    }

    public void Update(int level){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                this.stage[i][j] = 0;
            }
        }

        ArrayList<Point> bodySnake = snake.getBody();
        for(int i = 0; i < bodySnake.size(); i++){
            this.stage[bodySnake.get(i).x][bodySnake.get(i).y] = i + 1;
        }

        if(level == 1){
            createBlock();
        }else{
            createBlockLevel2();
        }

        this.stage[food.getPosition().x][food.getPosition().y] = VALOR_FOOD;

    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood(){
        return food;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight()
    {
        return height;
    }


    public void createBlockLevel2(){
        createBlock();

        stage[width/2][height/2 -2] = VALOR_BLOCK;

        stage[width/2 +1][height/2 -2] = VALOR_BLOCK;
        stage[width/2 +2][height/2 -2] = VALOR_BLOCK;

        stage[width/2 -1][height/2 -2] = VALOR_BLOCK;
        stage[width/2 -2][height/2 -2] = VALOR_BLOCK;
//-------------------------------------------------------
        stage[width/2][height/2 + 2] = VALOR_BLOCK;

        stage[width/2 +1][height/2 +2] = VALOR_BLOCK;
        stage[width/2 +2][height/2 +2] = VALOR_BLOCK;

        stage[width/2 -1][height/2 +2] = VALOR_BLOCK;
        stage[width/2 -2][height/2 +2] = VALOR_BLOCK;


    }
}
