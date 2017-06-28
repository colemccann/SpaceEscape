package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class Redirect extends GameObject {

    String TAG = "Redirect";

    private int pixelsPerMeter;

    public Redirect(Context context, float worldLocationX,
                    float worldLocationY, int pixelsPerMeter,
                    char type) {
        super(context);

        this.pixelsPerMeter = pixelsPerMeter;

        setType(type);
        switch (type) {
            case Constants.Types.REDIRECT_UP:
                setFacingAngle(360);
                break;
            case Constants.Types.REDIRECT_DOWN:
                setFacingAngle(180);
                break;
            case Constants.Types.REDIRECT_RIGHT:
                setFacingAngle(270);
                break;
            case Constants.Types.REDIRECT_LEFT:
                setFacingAngle(90);
                break;
        }
        setWorldLocation(worldLocationX, worldLocationY);

        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;

        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), halfW);
        setCollisionPackage(collisionPackage);

        float[] redirectVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };
        setVertices(redirectVertices);

        float[] textureVertices = new float[] {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
        };
        setTextureVertices(textureVertices);

        setTextureResource(R.drawable.redirect);
    }

    public void redirect(GameObject gameObject) {
        int buffer = pixelsPerMeter / 6;
        switch (gameObject.getType()) {
            case Constants.Types.PLAYER:
                if (gameObject.getFacingAngle() != getFacingAngle()) {
                    if (gameObject.getWorldLocation().x > getWorldLocation().x - buffer &&
                            gameObject.getWorldLocation().x < getWorldLocation().x + buffer) {
                        // Close to the center on the x axis
                        if (gameObject.getWorldLocation().y > getWorldLocation().y - buffer &&
                                gameObject.getWorldLocation().y < getWorldLocation().y + buffer) {
                            // Close to the center on the y axis
                            // Time to redirect
                            gameObject.setWorldLocation(getWorldLocation().x, getWorldLocation().y);
                            gameObject.setFacingAngle(getFacingAngle());
                        }
                    }
                }
                break;
            case Constants.Types.ASTEROID:
                if (gameObject.getTravelingAngle() != getFacingAngle()) {
                    if ((gameObject.getTravelingAngle() == Constants.Directions.DOWN &&
                            gameObject.getWorldLocation().y < getWorldLocation().y) ||
                            (gameObject.getTravelingAngle() == Constants.Directions.UP &&
                            gameObject.getWorldLocation().y > getWorldLocation().y) ||
                            (gameObject.getTravelingAngle() == Constants.Directions.RIGHT &&
                            gameObject.getWorldLocation().x > getWorldLocation().x) ||
                            (gameObject.getTravelingAngle() == Constants.Directions.LEFT &&
                            gameObject.getWorldLocation().x < getWorldLocation().x)) {
                            gameObject.setWorldLocation(getWorldLocation().x, getWorldLocation().y);
                            gameObject.redirect(getFacingAngle());
                    }
                }
        }
    }
}
