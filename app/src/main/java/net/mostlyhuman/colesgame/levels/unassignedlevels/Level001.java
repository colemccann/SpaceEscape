package net.mostlyhuman.colesgame.levels.unassignedlevels;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/15/2017.
 */

public class Level001 extends LevelData {

    public Level001() {
        tiles = new ArrayList<>();
        this.tiles.add("#p...........");
        this.tiles.add("..........#.#");
        this.tiles.add("...........he");
        this.tiles.add(".....b......#");
        this.tiles.add("......#....hb");

        asteroidDirections = new int[]{};

        doorStates = new boolean[]{false, false};

        doorKeys = new int[]{1, 2};

        buttonStates = new boolean[]{false, false};

        buttonKeys = new int[]{2, 1};

        warpTargets = new String[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = Constants.MapOrientation.horizontal;
    }
}
