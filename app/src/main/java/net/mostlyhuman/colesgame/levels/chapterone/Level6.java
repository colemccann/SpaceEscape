package net.mostlyhuman.colesgame.levels.chapterone;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 7/26/2017.
 */

public class Level6 extends LevelData {

    public Level6() {

        tiles = new ArrayList<>();
        this.tiles.add("#p.............");
        this.tiles.add("............#.#");
        this.tiles.add(".............he");
        this.tiles.add(".......b......#");
        this.tiles.add("........#....hb");

        asteroidDirections = new int[]{};

        doorStates = new boolean[]{};

        doorKeys = new int[]{1, 2};

        buttonStates = new boolean[]{false, false};

        buttonKeys = new int[]{2, 1};

        warpTargets = new String[]{};

        turretMovement = new boolean[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = Constants.MapOrientation.horizontal;
    }
}
