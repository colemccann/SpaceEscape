package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.util.Log;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

import java.util.Random;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class Block extends GameObject {

    String TAG = "Block";

    private int pixelsPerMeter;

    public Block(Context context, float worldLocationX,
                 float worldLocationY, int pixelsPerMeter) {
        super(context);

        this.pixelsPerMeter = pixelsPerMeter;

        setType(Constants.Types.BLOCK);
        setWorldLocation(worldLocationX, worldLocationY);

        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;

        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), halfW);
        setCollisionPackage(collisionPackage);

        float[] blockVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };
        setVertices(blockVertices);

        float left;
        float right;
        float top;
        float bottom;

        Random r = new Random();
        int whichBlock = r.nextInt(3);
        switch (whichBlock) {
            case 0:
                left = 0;
                right = .2f;
                top = .4f;
                bottom = .2f;
                setTextureVertices(left, right, 1 - top, 1 - bottom);
                break;
            case 1:
                left = 0;
                right = .2f;
                top = .2f;
                bottom = 0;
                setTextureVertices(left, right, 1 - top, 1 - bottom);
                break;
            case 2:
                left = .2f;
                right = .4f;
                top = .2f;
                bottom = 0;
                setTextureVertices(left, right, 1 - top, 1 - bottom);
                break;
        }
    }

    public void reposition(GameObject gameObject) {
        switch (gameObject.getType()) {
            case Constants.Types.PLAYER:
                if (gameObject.getFacingAngle() == 360) {
                    // Facing up
                    gameObject.setWorldLocation(
                            gameObject.getWorldLocation().x,
                            getWorldLocation().y - pixelsPerMeter);
                } else if (gameObject.getFacingAngle() == 90) {
                    // Facing left
                    gameObject.setWorldLocation(
                            getWorldLocation().x + pixelsPerMeter,
                            gameObject.getWorldLocation().y);
                } else if (gameObject.getFacingAngle() == 180) {
                    // Facing down
                    gameObject.setWorldLocation(
                            gameObject.getWorldLocation().x,
                            getWorldLocation().y + pixelsPerMeter);
                } else if (gameObject.getFacingAngle() == 270) {
                    // Facing right
                    gameObject.setWorldLocation(
                            getWorldLocation().x - pixelsPerMeter,
                            gameObject.getWorldLocation().y);
                }
                break;
        }
    }
}
