package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.util.Log;

import net.mostlyhuman.colesgame.helpers.Constants;


/**
 * Created by CaptainMcCann on 6/30/2017.
 */

public class Border extends GameObject {

    private static final String TAG = "Border";

    public Border(Context context, float mapWidth, float mapHeight, int pixelsPerMeter) {
        super(context);

        setType(Constants.Types.BORDER);

        int buffer = pixelsPerMeter / 2;
        setWorldLocation((mapWidth / 2) - buffer, (-mapHeight / 2) + buffer);

        Log.d(TAG, "Border Center: " + getWorldLocation());

        setSize(mapWidth, mapHeight);

        float[] borderVertices = new float[] {

                -mapWidth / 2, -mapHeight / 2, 1.0f, 1.0f, 1.0f,
                mapWidth / 2, -mapHeight / 2,  1.0f, 1.0f, 1.0f,

                mapWidth / 2, - mapHeight / 2,  1.0f, 1.0f, 1.0f,
                mapWidth / 2, mapHeight / 2,  1.0f, 1.0f, 1.0f,

                mapWidth / 2, mapHeight / 2,  1.0f, 1.0f, 1.0f,
                -mapWidth / 2, mapHeight / 2,  1.0f, 1.0f, 1.0f,

                -mapWidth / 2, mapHeight / 2,  1.0f, 1.0f, 1.0f,
                -mapWidth / 2, -mapHeight / 2,  1.0f, 1.0f, 1.0f,
        };

        setVertices(borderVertices);
    }
}
