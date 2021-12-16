package com.snakegdx.game.Models;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    LinkedList<Point> body = new LinkedList<>();

    public Point getHead() {
        return body.getFirst();
    }
    public Point getTail() {
        return body.getLast();
    }
    public Point addTail(Point area) {
        this.body.addLast(area);
        return area;
    }
    public LinkedList<Point> getBody(){
        return body;
    }
}
