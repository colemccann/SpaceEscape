package net.mostlyhuman.colesgame.levels.chapter2;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level12 extends LevelData {


    public Level12() {

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

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{Constants.Directions.RIGHT};

        mapOrientation = HORIZONTAL;
    }
}
