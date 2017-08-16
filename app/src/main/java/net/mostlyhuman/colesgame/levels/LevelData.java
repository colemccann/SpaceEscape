package net.mostlyhuman.colesgame.levels;

import java.util.ArrayList;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class LevelData {

    protected static final boolean OPEN = true;
    protected static final boolean CLOSED = false;

    public ArrayList<String> tiles;
    public int[] asteroidDirections;
    public boolean[] doorStates;
    public int[] doorKeys;
    public boolean[] buttonStates;
    public int[] buttonKeys;
    public String[] warpTargets;
    public int[] turretFacingAngles;
    public int mapOrientation;
}
