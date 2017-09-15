package net.mostlyhuman.colesgame.levels.chapter2;


import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level15 extends LevelData {


    public Level15() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("........");
        this.tiles.add(".a.p....");
        this.tiles.add("......a.");
        this.tiles.add("....e...");
        this.tiles.add(".....bv.");
        this.tiles.add("........");
        this.tiles.add("..a...a.");
        this.tiles.add("..a....a");


        asteroidDirections = new int[]{0, 0, 0, 0, 0, 0};

        doorStates = new boolean[]{CLOSED};

        doorKeys = new int[]{1};

        buttonStates = new boolean[]{CLOSED};

        buttonKeys = new int[]{1};

        warpTypes = null;
        warpTeleportTargets = null;
        warpDimensionalTargets = null;

        turretFacingAngles = null;

        mapOrientation = HORIZONTAL;
    }
}
