package com.snakegdx.game.Models;

import com.snakegdx.game.Screens.GameScreen;
import com.snakegdx.game.SnakeGdxGame;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {

    private final Point position;
    private int length;
    private char direction;
    private final ArrayList<Point> body;

    public Snake(Point position){
        this.position = position;
        this.length = 1;
        this.direction = 'S';
        this.body = new ArrayList<>();
        this.body.add(this.position);
    }

    public void changeDirection(char newDirection){
        direction = newDirection;
    }

    public void move(){
        switch(direction)
        {
            case 'U':
                position.y += 1;
                break;

            case 'D':
                position.y -= 1;
                break;

            case 'L':
                position.x -= 1;
                break;

            case 'R':
                position.x += 1;
                break;
            default:
                break;

        }

        body.add(new Point(position.x, position.y));
       if(body.size() > length){
            body.remove(0);
        }
    }


    public boolean checkEatFood(Point foodPosition){
        return position.x == foodPosition.x && position.y == foodPosition.y;
    }

    public boolean checkCollision(int width, int height, int [][] stage, int block){
        boolean collision = false;
        if(position.x > width - 2 || position.x < 1 ||
            position.y > height - 2 || position.y < 1){
            collision = true;
        }

        for (int i = 0; i < length - 2; i++){
            if (position.x == body.get(i).x && position.y == body.get(i).y) {
                collision = true;
                break;
            }
        }

        if(stage[position.x][position.y] == block){
            collision = true;
        }

        return collision;
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    public void increaseSnakeLength() {
        this.length += 1;
    }

}
