package net.mostlyhuman.colesgame.levels;

import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class LevelData {

    public static final String MAIN_LEVEL = "main";
    protected static final String WARP_LEVEL = "warp";

    protected static final boolean OPEN = true;
    protected static final boolean CLOSED = false;

    protected static final int UP = 360;
    protected static final int RIGHT = 270;
    protected static final int DOWN = 180;
    protected static final int LEFT = 90;

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    public ArrayList<String> tiles;
    public int[] asteroidDirections;
    public boolean[] doorStates;
    public int[] doorKeys;
    public boolean[] buttonStates;
    public int[] buttonKeys;
    public int[] turretFacingAngles;
    public int mapOrientation;
    public String levelType;

    public String[] warpDimensionalTargets;
    public PointF[] warpTeleportTargets;
    public char[] warpTypes;
}
