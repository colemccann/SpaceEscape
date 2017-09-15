package net.mostlyhuman.colesgame.levels.chapter1;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/26/2017.
 */

public class Level3 extends LevelData {

    public Level3() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("p......");
        this.tiles.add(".......");
        this.tiles.add("...#...");
        this.tiles.add("..#eh..");
        this.tiles.add("...#..#");
        this.tiles.add("...b...");
        this.tiles.add(".....#.");

        asteroidDirections = null;

        doorStates = new boolean[]{false};

        doorKeys = new int[]{0};

        buttonStates = new boolean[]{false};

        buttonKeys = new int[]{0};

        warpTypes = null;
        warpTeleportTargets = null;
        warpDimensionalTargets = null;

        turretFacingAngles = null;

        mapOrientation = HORIZONTAL;
    }

}
