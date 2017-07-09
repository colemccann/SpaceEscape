package net.mostlyhuman.colesgame.levels;

import net.mostlyhuman.colesgame.helpers.Constants;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class Level1 extends LevelData {

    public Level1() {
        tiles = new ArrayList<>();
        this.tiles.add("..p..");
        this.tiles.add(".....");
        this.tiles.add(".....");
        this.tiles.add(".....");
        this.tiles.add(".....");

        asteroidDirections = new int[]{
        };

        doorKeys = new int[]{};

        buttonStates = new boolean[]{};

        buttonKeys = new int[]{};

        warpTargets = new String[]{};

        turretMovement = new boolean[]{};

        turretFacingAngles = new int[]{};
    }


}
