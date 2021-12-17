package com.snakegdx.game.Models;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body;
    private int life;

    public Snake(Point position){
        this.body = new LinkedList<>();
        this.body.add(new Point(position.x, position.y));
        this.life = 3;
    }

    public Point getHead() {
        return body.getFirst();
    }

    public Point getTail()
    {
        return body.getLast();
    }

    public void addTail(Point area) {
        this.body.addLast(area);
    }

    public LinkedList<Point> getBody(){
        return body;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }
}
