package net.mostlyhuman.colesgame.levels.chapter2;

import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level18 extends LevelData {


    public Level18() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("....#.");
        this.tiles.add("....e.");
        this.tiles.add(".G.ax.");
        this.tiles.add("......");
        this.tiles.add("......");
        this.tiles.add("......");
        this.tiles.add("....pa");
        this.tiles.add("a.....");


        asteroidDirections = new int[]{0, 0, 0, 0};

        doorStates = null;

        doorKeys = null;

        buttonStates = null;

        buttonKeys = null;

        warpTypes = null;
        warpTeleportTargets = null;
        warpDimensionalTargets = null;

        turretFacingAngles = new int[]{RIGHT};

        mapOrientation = HORIZONTAL;
    }
}
