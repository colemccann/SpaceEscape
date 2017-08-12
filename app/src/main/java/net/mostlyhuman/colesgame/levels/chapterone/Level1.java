package net.mostlyhuman.colesgame.levels.chapterone;


import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class Level1 extends LevelData {

    public Level1() {
        tiles = new ArrayList<>();
        this.tiles.add("p.#e");
        this.tiles.add("....");
        this.tiles.add(".#.#");
        this.tiles.add("....");


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
