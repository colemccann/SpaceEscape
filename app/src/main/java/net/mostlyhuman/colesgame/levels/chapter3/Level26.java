package net.mostlyhuman.colesgame.levels.chapter3;

import android.graphics.PointF;

import net.mostlyhuman.colesgame.gameobjects.Warp;
import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.levels.LevelData;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 8/24/2017.
 */

public class Level26 extends LevelData {

    public Level26(char version, int pixelsPerMeter, boolean doorState) {

        switch (version) {
            case 'a':
                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("w#w#ww#w");
                this.tiles.add(".#.#..#.");
                this.tiles.add(".#.#..#.");
                this.tiles.add("p#.#..#.");
                this.tiles.add(".#v#..#.");
                this.tiles.add(".#.#..#.");
                this.tiles.add(".#.#..#.");
                this.tiles.add("w#w#.b#w");


                asteroidDirections = null;

                doorStates = new boolean[]{doorState};

                doorKeys = new int[]{0};

                buttonStates = new boolean[]{doorState};

                buttonKeys = new int[]{0};

                warpTypes = new char[]{Warp.TELEPORT, Warp.TELEPORT, Warp.DIMENSIONAL, Warp.DIMENSIONAL,
                        Warp.TELEPORT, Warp.DIMENSIONAL, Warp.TELEPORT, Warp.DIMENSIONAL};

                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_SIX_WC,
                        Constants.Levels.TWENTY_SIX_WA, Constants.Levels.TWENTY_SIX_WB,
                        Constants.Levels.TWENTY_SIX_WD};

                warpTeleportTargets = new PointF[]{
                        new PointF(2 * pixelsPerMeter, -6 * pixelsPerMeter),
                        new PointF(7 * pixelsPerMeter, -pixelsPerMeter),
                        new PointF(2 * pixelsPerMeter, -pixelsPerMeter),
                        new PointF(0, -pixelsPerMeter)
                };

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;
            case 'x':
                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("w#w#ww#w");
                this.tiles.add(".#.#..#.");
                this.tiles.add(".#.#..#.");
                this.tiles.add("p#.#..#.");
                this.tiles.add(".#v#..#.");
                this.tiles.add(".#.#..#.");
                this.tiles.add(".#.#..#.");
                this.tiles.add("w#w#.b#w");


                asteroidDirections = null;

                doorStates = new boolean[]{doorState};

                doorKeys = new int[]{0};

                buttonStates = new boolean[]{doorState};

                buttonKeys = new int[]{0};

                warpTypes = new char[]{Warp.TELEPORT, Warp.TELEPORT, Warp.DIMENSIONAL, Warp.DIMENSIONAL,
                        Warp.TELEPORT, Warp.DIMENSIONAL, Warp.TELEPORT, Warp.DIMENSIONAL};

                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_SIX_WC,
                        Constants.Levels.TWENTY_SIX_WA, Constants.Levels.TWENTY_SIX_WB,
                        Constants.Levels.TWENTY_SIX_WD};

                warpTeleportTargets = new PointF[]{
                        new PointF(2 * pixelsPerMeter, -6 * pixelsPerMeter),
                        new PointF(7 * pixelsPerMeter, -pixelsPerMeter),
                        new PointF(2 * pixelsPerMeter, -pixelsPerMeter),
                        new PointF(0, -pixelsPerMeter)
                };

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;
            case 'b':
                levelType = MAIN_LEVEL;

                tiles = new ArrayList<>();
                this.tiles.add("w#w#ww#w");
                this.tiles.add(".#.#p.#.");
                this.tiles.add(".#.#..#.");
                this.tiles.add(".#.#..#.");
                this.tiles.add(".#v#..#.");
                this.tiles.add(".#.#..#.");
                this.tiles.add(".#.#..#.");
                this.tiles.add("w#w#.b#w");


                asteroidDirections = null;

                doorStates = new boolean[]{doorState};

                doorKeys = new int[]{0};

                buttonStates = new boolean[]{doorState};

                buttonKeys = new int[]{0};

                warpTypes = new char[]{Warp.TELEPORT, Warp.TELEPORT, Warp.DIMENSIONAL, Warp.DIMENSIONAL,
                        Warp.TELEPORT, Warp.DIMENSIONAL, Warp.TELEPORT, Warp.DIMENSIONAL};

                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_SIX_WC,
                        Constants.Levels.TWENTY_SIX_WA, Constants.Levels.TWENTY_SIX_WB,
                        Constants.Levels.TWENTY_SIX_WD};

                warpTeleportTargets = new PointF[]{
                        new PointF(2 * pixelsPerMeter, -6 * pixelsPerMeter),
                        new PointF(7 * pixelsPerMeter, -pixelsPerMeter),
                        new PointF(2 * pixelsPerMeter, -pixelsPerMeter),
                        new PointF(0, -pixelsPerMeter)
                };

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
        }
    }

    public Level26(String version) {

        switch (version) {
            case "wa":
                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                tiles.add("p..#.b");
                tiles.add(".#....");
                tiles.add("..xaG.");
                tiles.add("..wv..");
                tiles.add("......");
                tiles.add("....#.");

                asteroidDirections = new int[]{0};

                doorStates = new boolean[]{CLOSED};

                doorKeys = new int[]{0};

                buttonStates = new boolean[]{CLOSED};

                buttonKeys = new int[]{0};

                warpTypes = new char[]{Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_SIX_X};
                warpTeleportTargets = null;

                turretFacingAngles = new int[]{LEFT};

                mapOrientation = HORIZONTAL;
                break;
            case "wb":
                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                tiles.add("..##..");
                tiles.add("#G.w..");
                tiles.add(".a...l");
                tiles.add(".G.B..");
                tiles.add(".....p");
                tiles.add("...#..");

                asteroidDirections = new int[]{0};

                doorStates = null;

                doorKeys = null;

                buttonStates = null;

                buttonKeys = null;

                warpTypes = new char[]{Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_SIX_B};
                warpTeleportTargets = null;

                turretFacingAngles = new int[]{DOWN, UP, UP};

                mapOrientation = HORIZONTAL;
                break;
            case "wc":
                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                tiles.add("...#..");
                tiles.add("...#..");
                tiles.add(".pw#..");
                tiles.add("...#..");
                tiles.add("...#..");
                tiles.add("...#.e");

                asteroidDirections = null;

                doorStates = null;

                doorKeys = null;

                buttonStates = null;

                buttonKeys = null;

                warpTypes = new char[]{Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_SIX_B};
                warpTeleportTargets = null;

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;
            case "wd":
                levelType = WARP_LEVEL;

                tiles = new ArrayList<>();
                tiles.add("...#.p");
                tiles.add("...#..");
                tiles.add("..w#..");
                tiles.add("...#..");
                tiles.add("...#..");
                tiles.add("...#.e");

                asteroidDirections = null;

                doorStates = null;

                doorKeys = null;

                buttonStates = null;

                buttonKeys = null;

                warpTypes = new char[]{Warp.DIMENSIONAL};
                warpDimensionalTargets = new String[]{Constants.Levels.TWENTY_SIX_B};
                warpTeleportTargets = null;

                turretFacingAngles = null;

                mapOrientation = HORIZONTAL;
                break;
        }
    }
}
