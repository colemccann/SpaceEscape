package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.util.Log;

import net.mostlyhuman.colesgame.game.ColorShaderProgram;
import net.mostlyhuman.colesgame.helpers.Constants;


/**
 * Created by CaptainMcCann on 6/30/2017.
 */

public class Border extends GameObject {

    private static final String TAG = "Border";

    public Border(Context context, float mapWidth, float mapHeight) {
        super(context);

        setType(Constants.Types.BORDER);

        setWorldLocation(mapWidth / 2, -mapHeight / 2);

        Log.d(TAG, "Border Center: " + getWorldLocation());

        setSize(mapWidth, mapHeight);

        float[] borderVertices = new float[] {
                -mapWidth / 2, -mapHeight / 2, 0,
                mapWidth / 2, -mapHeight / 2, 0,

                mapWidth / 2, - mapHeight / 2, 0,
                mapWidth / 2, mapHeight / 2, 0,

                mapWidth / 2, mapHeight / 2, 0,
                -mapWidth / 2, mapHeight / 2, 0,

                -mapWidth / 2, mapHeight / 2, 0
                -mapWidth / 2, -mapHeight / 2, 0
        };

        setVertices(borderVertices);
    }

    @Override
    public void draw(float[] viewportMatrix, ColorShaderProgram shaderProgram) {
        super.draw(viewportMatrix, shaderProgram);

        //Log.d(TAG, "Drawing Border");
    }
}
