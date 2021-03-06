package net.mostlyhuman.colesgame.levels.chapter2;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level12 extends LevelData {


    public Level12() {

        levelType = MAIN_LEVEL;

        tiles = new ArrayList<>();
        this.tiles.add("#dp");
        this.tiles.add("#..");
        this.tiles.add("#..");
        this.tiles.add("Ba.");
        this.tiles.add("#u.");
        this.tiles.add(".e.");
        this.tiles.add("...");
        this.tiles.add("..#");


        asteroidDirections = new int[]{Constants.Directions.UP};

        doorStates = null;

        doorKeys = null;

        buttonStates = null;

        buttonKeys = null;

        warpTypes = null;
        warpTeleportTargets = null;
        warpDimensionalTargets = null;

        turretFacingAngles = new int[]{Constants.Directions.RIGHT};

        mapOrientation = HORIZONTAL;
    }
}
