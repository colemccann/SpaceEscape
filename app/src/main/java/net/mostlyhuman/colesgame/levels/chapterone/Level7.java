package net.mostlyhuman.colesgame.levels.chapterone;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/26/2017.
 */

public class Level7 extends LevelData {

    // TODO: 8/13/2017 this level is too hard this early in the game

    public Level7() {
        tiles = new ArrayList<>();
        this.tiles.add(".....e");
        this.tiles.add("...GB.");
        this.tiles.add("...G..");
        this.tiles.add("#.....");
        this.tiles.add("..G...");
        this.tiles.add("..#..p");


        asteroidDirections = new int[]{};

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{
                Constants.Directions.UP,
                Constants.Directions.RIGHT,
                Constants.Directions.LEFT,
                Constants.Directions.RIGHT};

        mapOrientation = Constants.MapOrientation.horizontal;
    }
}
