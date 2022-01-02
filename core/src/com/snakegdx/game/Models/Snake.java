package com.snakegdx.game.Models;

import com.snakegdx.game.SnakeGdxGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

public class Snake {

    private Point position;
    private int length;
    private int velocity;
    private char direction;
    private ArrayList<Point> body;

    public Snake(Point position, int velocity){
        this.position = position;
        this.velocity = velocity;
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
                position.y += velocity;
                break;

            case 'D':
                position.y -= velocity;
                break;

            case 'L':
                position.x -= velocity;
                break;

            case 'R':
                position.x += velocity;
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

    public boolean checkCollision(){
        boolean collision = false;
        if(position.x > SnakeGdxGame.width - 2 || position.x < 1 ||
            position.y > SnakeGdxGame.height - 2 || position.y < 1){
            collision = true;
        }

        for (int i = 0; i < length - 2; i++){
            if (position.x == body.get(i).x && position.y == body.get(i).y) {
                collision = true;
                break;
            }
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
