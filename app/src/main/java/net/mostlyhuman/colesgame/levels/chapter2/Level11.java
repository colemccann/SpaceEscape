package net.mostlyhuman.colesgame.levels.chapter2;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level11 extends LevelData {

    public Level11() {

        tiles = new ArrayList<>();
        this.tiles.add("#p#");
        this.tiles.add(".a.");
        this.tiles.add("...");
        this.tiles.add("e..");
        this.tiles.add("...");
        this.tiles.add("...");
        this.tiles.add(".u.");
        this.tiles.add("...");


        asteroidDirections = new int[]{0};

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = Constants.MapOrientation.vertical;
    }
}
