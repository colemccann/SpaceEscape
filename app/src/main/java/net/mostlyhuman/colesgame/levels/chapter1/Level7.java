package net.mostlyhuman.colesgame.levels.chapter1;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/26/2017.
 */

public class Level7 extends LevelData {

    public Level7() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add(".....e");
        this.tiles.add("...GB.");
        this.tiles.add("...G..");
        this.tiles.add("#.....");
        this.tiles.add("..G...");
        this.tiles.add("..#..p");


        asteroidDirections = null;

        doorStates = null;

        doorKeys = null;

        buttonStates = null;

        buttonKeys = null;

        warpTypes = null;
        warpTeleportTargets = null;
        warpDimensionalTargets = new String[]{};

        turretFacingAngles = new int[]{
                UP, RIGHT, LEFT, RIGHT};

        mapOrientation = HORIZONTAL;
    }
}
