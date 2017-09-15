package net.mostlyhuman.colesgame.levels.chapter3;

import android.graphics.PointF;

import net.mostlyhuman.colesgame.gameobjects.Warp;
import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level21 extends LevelData {

    public Level21(char version) {

        switch (version) {
            case 'a':

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("........");
                this.tiles.add(".e......");
                this.tiles.add("########");
                this.tiles.add(".w......");
                this.tiles.add("........");
                this.tiles.add("........");
                this.tiles.add("........");
                this.tiles.add("......p.");

                asteroidDirections = null;

                doorStates = null;

                doorKeys = null;

                buttonStates = null;

                buttonKeys = null;

                warpTypes = new char[]{Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_ONE_W};
                warpTeleportTargets = null;

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;

            case 'b':

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("........");
                this.tiles.add(".e....p.");
                this.tiles.add("########");
                this.tiles.add(".w......");
                this.tiles.add("........");
                this.tiles.add("........");
                this.tiles.add("........");
                this.tiles.add("........");

                asteroidDirections = null;

                doorStates = null;

                doorKeys = null;

                buttonStates = null;

                buttonKeys = null;

                warpTypes = new char[]{Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_ONE_W};
                warpTeleportTargets = null;

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;

            case 'w':

                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("......");
                this.tiles.add("p....w");
                this.tiles.add("......");

                asteroidDirections = null;

                doorStates = null;

                doorKeys = null;

                buttonStates = null;

                buttonKeys = null;

                warpTypes = new char[]{Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_ONE_B};
                warpTeleportTargets = null;

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;
        }
    }
}
