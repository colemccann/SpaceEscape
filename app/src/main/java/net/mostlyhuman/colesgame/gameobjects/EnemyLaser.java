package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

import net.mostlyhuman.colesgame.R;

/**
 * Created by CaptainMcCann on 5/22/2017.
 */

public class EnemyLaser extends Laser {

    public EnemyLaser(Context context, float worldLocationX,
                      float worldLocationY, int pixelsPerMeter,
                      int laserID) {
        super(context, worldLocationX, worldLocationY, pixelsPerMeter);

        // Set Texture Vertices here
        float left;
        float right;
        float top;
        float bottom;

        setTextureVertices(left, right, top, bottom);

        setLaserID(laserID);
    }

}
