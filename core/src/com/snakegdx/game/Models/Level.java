package com.snakegdx.game.Models;

import java.util.ArrayList;

public class Level {

    ArrayList<int[][]> levels;

    public Level(String name, String levels){
        this.levels = convertToLevels(name, levels);
    }

    private ArrayList<int[][]> convertToLevels(String name, String levels) {
        return null;
    }

}
