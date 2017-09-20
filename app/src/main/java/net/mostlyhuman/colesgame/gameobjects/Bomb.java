package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.game.SoundManager;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class Bomb extends GameObject {

    String TAG = "Bomb";

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

        // Set Texture Vertices here
        float left = .6f;
        float right = .8f;
        float top = .6f;
        float bottom = .4f;

        setTextureVertices(left, right, 1 - top, 1 - bottom);
    }

    @Override
    public void destroy(SoundManager sm) {
        super.destroy(sm);
        sm.playSound(Constants.Sounds.EXPLOSION);

    }

    public void kill(GameObject gameObject, SoundManager sm) {
        switch (gameObject.getType()) {
            case Constants.Types.PLAYER:
                gameObject.destroy(sm);
                break;
        }
    }
}
