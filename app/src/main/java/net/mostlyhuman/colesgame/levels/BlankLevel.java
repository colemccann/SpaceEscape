package net.mostlyhuman.colesgame.levels;

import net.mostlyhuman.colesgame.helpers.Constants;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/15/2017.
 */

public class BlankLevel extends LevelData {

    public BlankLevel() {

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

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = Constants.MapOrientation.horizontal;
    }
}
