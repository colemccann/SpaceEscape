package net.mostlyhuman.colesgame.levels.chapter3;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level24 extends LevelData {

    public Level24() {

        tiles = new ArrayList<>();
        this.tiles.add("...#..");
        this.tiles.add("......");
        this.tiles.add("..dap.");
        this.tiles.add("......");
        this.tiles.add("..u...");
        this.tiles.add("......");


        asteroidDirections = new int[]{UP};

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = HORIZONTAL;
    }
}
