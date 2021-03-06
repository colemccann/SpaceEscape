package net.mostlyhuman.colesgame.levels.chapter2;


import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level17 extends LevelData {


    public Level17() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("x...x.ax");
        this.tiles.add(".......#");
        this.tiles.add("...ax...");
        this.tiles.add("#x.x....");
        this.tiles.add("....a..x");
        this.tiles.add(".......e");
        this.tiles.add(".x.a...#");
        this.tiles.add("...x..p.");


        asteroidDirections = new int[]{0, 0, 0, 0};

        doorStates = null;

        doorKeys = null;

        buttonStates = null;

        buttonKeys = null;

        warpTypes = null;
        warpTeleportTargets = null;
        warpDimensionalTargets = null;

        turretFacingAngles = null;

        mapOrientation = HORIZONTAL;
    }
}
