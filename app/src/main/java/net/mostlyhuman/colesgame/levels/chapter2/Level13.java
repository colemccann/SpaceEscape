package net.mostlyhuman.colesgame.levels.chapter2;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;


/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level13 extends LevelData {


    public Level13() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("...G..");
        this.tiles.add("...a..");
        this.tiles.add("......");
        this.tiles.add("..#...");
        this.tiles.add("...R..");
        this.tiles.add("....e.");
        this.tiles.add(".....#");
        this.tiles.add("..p...");


        asteroidDirections = new int[]{DOWN};

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{DOWN, DOWN};

        mapOrientation = HORIZONTAL;
    }
}
