package net.mostlyhuman.colesgame.levels.chapterone;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/26/2017.
 */

public class Level3 extends LevelData {

    public Level3() {
        tiles = new ArrayList<>();
        this.tiles.add(".......");
        this.tiles.add(".......");
        this.tiles.add("...#...");
        this.tiles.add("..#eh..");
        this.tiles.add("..p#..#");
        this.tiles.add("...b...");
        this.tiles.add(".....#.");

        asteroidDirections = new int[]{};

        doorStates = new boolean[]{};

        doorKeys = new int[]{1};

        buttonStates = new boolean[]{false};

        buttonKeys = new int[]{1};

        warpTargets = new String[]{};

        turretMovement = new boolean[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = Constants.MapOrientation.horizontal;
    }
}
