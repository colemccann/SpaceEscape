package net.mostlyhuman.colesgame.levels.chapterone;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/26/2017.
 */

public class Level5 extends LevelData {

    public Level5() {
        tiles = new ArrayList<>();
        this.tiles.add(".#..#p#e");
        this.tiles.add(".......v");
        this.tiles.add("........");
        this.tiles.add("..#b#...");
        this.tiles.add("...v....");
        this.tiles.add("....#...");
        this.tiles.add("......#.");
        this.tiles.add("..#...#b");

        asteroidDirections = new int[]{};

        doorStates = new boolean[]{false, false};

        doorKeys = new int[]{1, 2};

        buttonStates = new boolean[]{false, false};

        buttonKeys = new int[]{1, 2};

        warpTargets = new String[]{};

        turretMovement = new boolean[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = Constants.MapOrientation.horizontal;
    }
}
