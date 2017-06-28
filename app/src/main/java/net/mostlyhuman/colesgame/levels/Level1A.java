package net.mostlyhuman.colesgame.levels;

import net.mostlyhuman.colesgame.helpers.Constants;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class Level1A extends LevelData {

    public Level1A() {
        tiles = new ArrayList<>();
        this.tiles.add("............");
        this.tiles.add("..#.....#...");
        this.tiles.add(".#......b#..");
        this.tiles.add("............");
        this.tiles.add(".....h......");
        this.tiles.add(".....t......");
        this.tiles.add("..p..h......");
        this.tiles.add(".#b......#..");
        this.tiles.add("..#.....#...");
        this.tiles.add("............");

        asteroidDirections = new int[]{};

        doorKeys = new int[]{1, 2};

        buttonStates = new boolean[]{false, false};

        buttonKeys = new int[]{2, 1};

        warpTargets = new String[]{};

        turretMovement = new boolean[]{true};

        turretFacingAngles = new int[]{Constants.Directions.RIGHT};
    }


}
