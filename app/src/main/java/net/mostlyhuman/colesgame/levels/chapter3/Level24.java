package net.mostlyhuman.colesgame.levels.chapter3;


import android.util.Log;

import net.mostlyhuman.colesgame.gameobjects.Warp;
import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level24 extends LevelData {

    public Level24(String version) {

        switch (version) {
            case "a":

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("p.#..");
                this.tiles.add(".w#w.");
                this.tiles.add("#####");
                this.tiles.add(".w#e.");
                this.tiles.add("..#..");


                asteroidDirections = null;

                doorStates = null;

                doorKeys = null;

                buttonStates = null;

                buttonKeys = null;

                warpTypes = new char[]{Warp.DIMENSIONAL, Warp.DIMENSIONAL, Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_FOUR_WA,
                        Constants.Levels.TWENTY_FOUR_WB, Constants.Levels.TWENTY_FOUR_WC};
                warpTeleportTargets = null;

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;
            case "b":

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("..#.p");
                this.tiles.add(".w#w.");
                this.tiles.add("#####");
                this.tiles.add(".w#e.");
                this.tiles.add("..#..");


                asteroidDirections = null;

                doorStates = null;

                doorKeys = null;

                buttonStates = null;

                buttonKeys = null;

                warpTypes = new char[]{Warp.DIMENSIONAL, Warp.DIMENSIONAL, Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_FOUR_WA,
                        Constants.Levels.TWENTY_FOUR_WB, Constants.Levels.TWENTY_FOUR_WC};
                warpTeleportTargets = null;

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;
            case "c":

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("..#..");
                this.tiles.add(".w#w.");
                this.tiles.add("#####");
                this.tiles.add(".w#e.");
                this.tiles.add("p.#..");


                asteroidDirections = null;

                doorStates = null;

                doorKeys = null;

                buttonStates = null;

                buttonKeys = null;

                warpTypes = new char[]{Warp.DIMENSIONAL, Warp.DIMENSIONAL, Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_FOUR_WA,
                        Constants.Levels.TWENTY_FOUR_WB, Constants.Levels.TWENTY_FOUR_WC};
                warpTeleportTargets = null;

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;
            case "d":

                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("..#..");
                this.tiles.add(".w#w.");
                this.tiles.add("#####");
                this.tiles.add(".w#e.");
                this.tiles.add("..#.p");


                asteroidDirections = null;

                doorStates = null;

                doorKeys = null;

                buttonStates = null;

                buttonKeys = null;

                warpTypes = new char[]{Warp.DIMENSIONAL, Warp.DIMENSIONAL, Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_FOUR_WA,
                        Constants.Levels.TWENTY_FOUR_WB, Constants.Levels.TWENTY_FOUR_WC};
                warpTeleportTargets = null;

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;
            case "wa":

                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("p...#...");
                this.tiles.add(".......B");
                this.tiles.add("r.......");
                this.tiles.add("#.......");
                this.tiles.add("..w.....");
                this.tiles.add(".u...#..");
                this.tiles.add("...#x...");
                this.tiles.add("........");


                asteroidDirections = new int[]{};

                doorStates = null;

                doorKeys = null;

                buttonStates = null;

                buttonKeys = null;

                warpTypes = new char[]{Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_FOUR_B};
                warpTeleportTargets = null;

                turretFacingAngles = new int[]{DOWN};

                mapOrientation = HORIZONTAL;
                break;
            case "wb":

                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add(".....#..");
                this.tiles.add(".......#");
                this.tiles.add(".d.p.a..");
                this.tiles.add("..w#....");
                this.tiles.add(".r.....#");
                this.tiles.add("...#....");
                this.tiles.add("........");
                this.tiles.add("#....u..");


                asteroidDirections = new int[]{0};

                doorStates = null;

                doorKeys = null;

                buttonStates = null;

                buttonKeys = null;

                warpTypes = new char[]{Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_FOUR_C};
                warpTeleportTargets = null;

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;
            case "wc":

                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("...p....");
                this.tiles.add(".....b..");
                this.tiles.add("#.......");
                this.tiles.add("...#...#");
                this.tiles.add("......hw");
                this.tiles.add(".v#....#");
                this.tiles.add(".b#.....");
                this.tiles.add("........");


                asteroidDirections = null;

                doorStates = new boolean[]{CLOSED, CLOSED};

                doorKeys = new int[]{1, 0};

                buttonStates = new boolean[]{CLOSED, CLOSED};

                buttonKeys = new int[]{0, 1};

                warpTypes = new char[]{Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_FOUR_D};
                warpTeleportTargets = null;

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;
        }
    }
}
