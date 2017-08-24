package net.mostlyhuman.colesgame.levels.chapter1;


import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 5/20/2017.
 */

public class Level2 extends LevelData {

    public Level2() {
        tiles = new ArrayList<>();
        this.tiles.add("...#e");
        this.tiles.add("#....");
        this.tiles.add("..#.#");
        this.tiles.add(".....");
        this.tiles.add(".#..p");

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
