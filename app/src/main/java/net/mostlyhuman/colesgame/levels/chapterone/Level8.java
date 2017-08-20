package net.mostlyhuman.colesgame.levels.chapterone;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/30/2017.
 */

public class Level8 extends LevelData {

    public Level8() {
        tiles = new ArrayList<>();
        this.tiles.add("..b#..e");
        this.tiles.add(".#.#..#");
        this.tiles.add("..hRh.b");
        this.tiles.add(".......");
        this.tiles.add(".......");
        this.tiles.add(".p.....");


        asteroidDirections = new int[]{};

        doorStates = new boolean[]{OPEN, OPEN};

        doorKeys = new int[]{1, 2};

        buttonStates = new boolean[]{true, true};

        buttonKeys = new int[]{2, 1};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{Constants.Directions.RIGHT};

        mapOrientation = Constants.MapOrientation.horizontal;
    }
}
