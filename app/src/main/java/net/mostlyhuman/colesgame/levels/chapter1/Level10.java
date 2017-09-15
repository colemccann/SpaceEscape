package net.mostlyhuman.colesgame.levels.chapter1;


import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/15/2017.
 */

public class Level10 extends LevelData {

    public Level10() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("...rd.");
        this.tiles.add("..#...");
        this.tiles.add("erp..d");
        this.tiles.add("ul#...");
        this.tiles.add("d...l.");
        this.tiles.add("ru.u.l");


        asteroidDirections = null;

        doorStates = null;

        doorKeys = null;

        buttonStates = null;

        buttonKeys = null;

        warpTypes = null;
        warpTeleportTargets = null;
        warpDimensionalTargets = new String[]{};

        turretFacingAngles = null;

        mapOrientation = HORIZONTAL;
    }
}
