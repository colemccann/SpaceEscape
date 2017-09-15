package net.mostlyhuman.colesgame.levels.chapter3;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level30 extends LevelData {

    public Level30() {

        tiles = new ArrayList<>();
        this.tiles.add("......");
        this.tiles.add("......");
        this.tiles.add("......");
        this.tiles.add("......");
        this.tiles.add("......");
        this.tiles.add("......");


        asteroidDirections = new int[]{};

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpDimensionalTargets = new String[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = HORIZONTAL;
    }
}
