package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class Bomb extends GameObject {

    String TAG = "Bomb";

    public CollisionPackage cp;

    public Bomb(Context context, float worldLocationX,
                float worldLocationY, int pixelsPerMeter) {
        super(context);

        setType(Constants.Types.BOMB);
        setWorldLocation(worldLocationX, worldLocationY);

        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;

        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), halfW);
        setCollisionPackage(collisionPackage);

        float[] bombVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };
        setVertices(bombVertices);

        float[] textureVertices = new float[] {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
        };
        setTextureVertices(textureVertices);
    }

    @Override
    public void destroy() {
         super.destroy();
        //// TODO: 5/29/2017 add animation
    }

    public void kill(GameObject gameObject) {
        switch (gameObject.getType()) {
            case Constants.Types.PLAYER:
                gameObject.destroy();
                //// TODO: 5/1/2017 ship explosion animation
                break;
            //// TODO: 5/1/2017 Add interaction for other game objects here
        }
    }
}
