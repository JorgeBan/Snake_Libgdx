package com.snakegdx.game.Models;


import com.snakegdx.game.Controllers.GameController;
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

    private GameController gameController;

    public Stage(int width, int height, int level) {
        this.width = width;
        this.height = height;
        this.food = new Food();
        this.stage = new int[width][height];
        this.levels = new ArrayList<>();
        init(level);
    }

    public void init(int level){
        this.snake = new Snake(new Point(width/2, height/2));
        loadLevels();
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
            Update(level);
        }else{
            createFood(level);
        }
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
            gameController.getSoundCollision().play();
            snake.decreaseLife();
            int snakeLife = snake.getLife();
            gameController.setScore(gameController.getScore()-Utilities.REDUCE_LIFE);
            this.snake = new Snake(new Point(width/2, height/2), snakeLife, gameController.getPreviousDirection());
            if(snake.getLife() < 1) {
                gameController.getSoundGameOver().play();
                gameController.setGameOver(true);
            }
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood(){
        return food;
    }


    public void setGameController(GameController gameController){
        this.gameController = gameController;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return  this.height;
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