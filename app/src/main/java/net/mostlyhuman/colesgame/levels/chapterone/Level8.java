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
        this.tiles.add("p....");
        this.tiles.add(".....");
        this.tiles.add("..e..");
        this.tiles.add(".....");
        this.tiles.add(".....");

        asteroidDirections = new int[]{};

        doorStates = new boolean[]{};

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretMovement = new boolean[]{};

        turretFacingAngles = new int[]{};

        mapOrientation = Constants.MapOrientation.horizontal;
    }
}
