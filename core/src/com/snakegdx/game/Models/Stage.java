package com.snakegdx.game.Models;

import com.snakegdx.game.SnakeGdxGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

public class Stage {
    private static final int VALOR_FOOD = 1001;
    private static final int VALOR_BLOCK = 1002;
    private final Snake snake;
    private final int width;
    private final int height;
    private final Food food;
    private final int[][] stage;

    public Stage(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(new Point(width/2, height/2), 1);
        this.food = new Food();
        this.stage = new int[width][height];
        init();
    }

    public void init(){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                this.stage[i][j] = 0;
            }
        }

        ArrayList<Point> bodySnake = snake.getBody();
        for(int i = 0; i < bodySnake.size(); i++){
            this.stage[bodySnake.get(i).x][bodySnake.get(i).y] = i + 1;
        }

        createBlock();
        createFood();


    }

    private void createBlock() {
        for (int i = 0; i < width; i++){
            stage[i][0] = VALOR_BLOCK;
            stage[i][height-1] = VALOR_BLOCK;
            stage[0][i] = VALOR_BLOCK;
            stage[height-1][i] = VALOR_BLOCK;
        }
    }

    public int[][] getStage() {
        return stage;
    }


    public void createFood(){
        this.food.generateFood(width, height);
        int foodX = this.food.getPosition().x;
        int foodY = this.food.getPosition().y;
        if(this.stage[foodX][foodY] == 0){
            this.stage[foodX][foodY] = VALOR_FOOD;
        }else{
            createFood();
        }
        Update();
    }

    public void Update(){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                this.stage[i][j] = 0;
            }
        }

        ArrayList<Point> bodySnake = snake.getBody();
        for(int i = 0; i < bodySnake.size(); i++){
            this.stage[bodySnake.get(i).x][bodySnake.get(i).y] = i + 1;
        }

        this.stage[food.getPosition().x][food.getPosition().y] = VALOR_FOOD;
        createBlock();
        //showMatrix();
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

    public void showMatrix(){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                System.out.print(stage[i][j]+"\t\t");
            }
            System.out.println("");
        }
    }
}
