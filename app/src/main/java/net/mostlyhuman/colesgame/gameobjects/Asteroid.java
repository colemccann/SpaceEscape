package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;

import java.util.Random;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class Asteroid extends GameObject {

    String TAG = "Asteroid";

    private float mAngle;
    private int pixelsPerMeter;
    private boolean beingRedirected;

    public Asteroid(Context context, float worldLocationX,
                    float worldLocationY, int pixelsPerMeter) {
        super(context);

        this.pixelsPerMeter = pixelsPerMeter;

        setType(Constants.Types.ASTEROID);

        setWorldLocation(worldLocationX, worldLocationY);

        setxVelocity(0);
        setyVelocity(0);

        setMaxSpeed(50);


        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;
        float hitboxSideLength = pixelsPerMeter / 3;

        CollisionPackage collisionPackage = new CollisionPackage(getWorldLocation(), hitboxSideLength);
        setCollisionPackage(collisionPackage);

        float[] asteroidVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };
        setVertices(asteroidVertices);

        float[] textureVertices = new float[] {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
        };
        setTextureVertices(textureVertices);

        Random r = new Random();
        int whichAsteroid = r.nextInt(2);
        if (whichAsteroid == 0) {
            setTextureResource(R.drawable.asteroid1);
        } else if (whichAsteroid == 1) {
            setTextureResource(R.drawable.asteroid2);
        }

        Random random = new Random();
        int rotationSpeed = random.nextInt(8);
        switch (rotationSpeed) {
            case 0:
                mAngle = 0.10f;
                break;
            case 1:
                mAngle = 0.20f;
                break;
            case 2:
                mAngle = 0.30f;
                break;
            case 3:
                mAngle = 0.40f;
                break;
            case 4:
                mAngle = 0.50f;
                break;
            case 5:
                mAngle = 0.60f;
                break;
            case 6:
                mAngle = 0.70f;
                break;
            case 7:
                mAngle = 0.80f;
                break;
        }
    }

    @Override
    public void update(float fps) {

        if (this.getFacingAngle() + mAngle > 360) {
            this.setFacingAngle(mAngle + getFacingAngle() - 360);
        } else {
            this.setFacingAngle(mAngle + getFacingAngle());
        }

        setxVelocity((float) (getSpeed() * Math.cos(Math.toRadians(getTravelingAngle() + 90))));

        setyVelocity((float) (getSpeed() * Math.sin(Math.toRadians(getTravelingAngle() + 90))));

        move(fps);

        getCollisionPackage().updateHitbox(getWorldLocation());
    }

    public void bounce() {
        setTravelingAngle(getTravelingAngle() + 180);
    }

    @Override
    public void redirect(float newTravelingAngle) {
        setTravelingAngle(newTravelingAngle);
    }

    public boolean isInFrontOf(PointF objectLocation, float objectFacingAngle) {
        boolean inFront = false;

        double angle;
        float distanceX = getWorldLocation().x - objectLocation.x;
        float distanceY = getWorldLocation().y - objectLocation.y;
        double distanceH = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        double sinValue = distanceY / distanceH;
        angle = (Math.acos(sinValue) * (180 / Math.PI));

        if (angle <= 45 || angle > 315) {
            if (objectFacingAngle == 360) {
                inFront = true;
            }
        } else if (angle <= 135 && angle > 45) {
            if (objectFacingAngle == 90) {
                inFront = true;
            }
        } else if (angle <= 225 && angle > 135) {
            if (objectFacingAngle == 180) {
                inFront = true;
            }
        } else if (angle <= 315 && angle > 225) {
            if (objectFacingAngle == 270) {
                inFront = true;
            }
        }
        return inFront;
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

    public boolean detectCollision(CollisionPackage cp2) {
        boolean collided = false;

        if (getCollisionPackage().right > cp2.left &&
                getCollisionPackage().left < cp2.right) {
            // Intersecting on the x-axis
            if (getCollisionPackage().top > cp2.bottom &&
                    getCollisionPackage().bottom < cp2.top) {
                // Intersecting on the y-axis also
                // Collided!
                collided = true;
            }
        }
        return collided;
    }

    @Override
    public void destroy() {
        setActive(false);
        //// TODO: 5/29/2017 add animation
    }

    public boolean isBeingRedirected() {
        return beingRedirected;
    }

    public void setBeingRedirected(boolean beingRedirected) {
        this.beingRedirected = beingRedirected;
    }
}
