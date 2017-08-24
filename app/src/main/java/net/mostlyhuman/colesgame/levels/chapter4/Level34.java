package net.mostlyhuman.colesgame.levels.chapter4;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level34 extends LevelData {

    public Level34() {

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
