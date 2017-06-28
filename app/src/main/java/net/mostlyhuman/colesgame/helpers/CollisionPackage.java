package net.mostlyhuman.colesgame.helpers;

import android.graphics.PointF;
import android.util.Log;

/**
 * Created by CaptainMcCann on 4/23/2017.
 */

public class CollisionPackage {
    String TAG = "Collision Package";

    private PointF worldLocation;
    private float halfSideLength;

    public float top;
    public float left;
    public float bottom;
    public float right;

    public CollisionPackage(PointF worldLocation, float halfSideLength) {
        this.worldLocation = worldLocation;
        this.halfSideLength = halfSideLength;

        this.top = worldLocation.y + halfSideLength;
        this.left = worldLocation.x - halfSideLength;
        this.bottom = worldLocation.y - halfSideLength;
        this.right = worldLocation.x + halfSideLength;
    }

    public void updateHitbox(PointF newWorldLocation) {
        this.worldLocation = newWorldLocation;

        this.top = worldLocation.y + halfSideLength;
        this.left = worldLocation.x - halfSideLength;
        this.bottom = worldLocation.y - halfSideLength;
        this.right = worldLocation.x + halfSideLength;
    }
}
