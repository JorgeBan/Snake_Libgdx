package com.snakegdx.game.Models;


import com.snakegdx.game.Screens.GameScreen;
import com.snakegdx.game.Utilities;
import java.awt.Point;
import java.util.ArrayList;

public class Stage {
    private  Snake snake;
    private final int width;
    private final int height;
    private final Food food;
    private int[][] stage;
    private final ArrayList<int[][]> levels;

    public Stage(int width, int height, int level) {
        this.width = width;
        this.height = height;
        this.food = new Food();
        this.stage = new int[width][height];
        this.levels = new ArrayList<>();
        init(level);
    }

    public void init(int level){
        loadLevels();
        this.snake = new Snake(new Point(width/2, height/2));
        for (int i = 0; i < width; i++){
            if (height >= 0) System.arraycopy(levels.get(level)[i], 0, this.stage[i], 0, height);
        }
        this.stage[snake.getBody().get(0).x][snake.getBody().get(0).y] = 1;

        createFood(level);

    }

    public int[][] getStage() {
        return stage;
    }


    public void createFood(int level){
        this.food.generateFood(width, height);
        int foodX = this.food.getPosition().x;
        int foodY = this.food.getPosition().y;
        if(this.stage[foodX][foodY] == 0){
            this.stage[foodX][foodY] = Utilities.VALOR_FOOD;
        }else{
            createFood(level);
        }
        Update(level);
    }

    public void Update(int level){
        for (int i = 0; i < width; i++){
            if (height >= 0) System.arraycopy(levels.get(level)[i], 0, this.stage[i], 0, height);
        }
        if(!getSnake().checkCollision(width, height, stage)){
            ArrayList<Point> snakeBody = snake.getBody();
            for (int i = 0 ; i < snakeBody.size(); i++){
                this.stage[snakeBody.get(i).x][snakeBody.get(i).y] = i+1;
            }

            this.stage[food.getPosition().x][food.getPosition().y] = Utilities.VALOR_FOOD;
        }else {
            snake.decreaseLife();
            int snakeLife = snake.getLife();
            GameScreen.score -= 30;
            this.snake = new Snake(new Point(width/2, height/2), snakeLife, GameScreen.previousDirection);
            if(snake.getLife() < 1) {
                GameScreen.gameOver = true;
            }
        }
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

    private void loadLevels(){
        int [][] level1 = {
                {1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002},
        };

        int [][] level2 = {
                {1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,1002,1002,1002,   0,   0,   0,   0,   0,1002,1002,1002,   0,1002},
                {1002,   0,1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002,   0,1002},
                {1002,   0,1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002,   0,1002},
                {1002,   0,1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002,   0,1002},
                {1002,   0,1002,1002,1002,   0,   0,   0,   0,   0,1002,1002,1002,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002},
        };

        int [][] level3 = {
                {1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002,   0,1002},
                {1002,   0,   0,1002,   0,   0,   0,   0,   0,   0,   0,1002,   0,   0,1002},
                {1002,   0,   0,   0,1002,   0,   0,   0,   0,   0,1002,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,1002,   0,   0,   0,1002,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,1002,   0,   0,   0,1002,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,1002,   0,   0,   0,   0,   0,1002,   0,   0,   0,1002},
                {1002,   0,   0,1002,   0,   0,   0,   0,   0,   0,   0,1002,   0,   0,1002},
                {1002,   0,1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,1002},
                {1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002,1002},
        };
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
    }
}