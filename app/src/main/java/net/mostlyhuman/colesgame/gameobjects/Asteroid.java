package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;

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
    private boolean isInFrontOfPlayer;

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
        float hitboxSideLength = pixelsPerMeter / 2.1f;

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

        setBitmap();

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

    private void setBitmap() {
        float left;
        float right;
        float top;
        float bottom;

        Random r = new Random();
        int whichAsteroid = r.nextInt(4);
        switch (whichAsteroid) {
            case 0:
                left = 0;
                right = .2f;
                top = 1;
                bottom = .8f;
                setTextureVertices(left, right, 1 - top, 1 - bottom);
                break;
            case 1:
                left = .2f;
                right = .4f;
                top = 1;
                bottom = .8f;
                setTextureVertices(left, right, 1 - top, 1 - bottom);
                break;
            case 2:
                left = .4f;
                right = .6f;
                top = 1;
                bottom = .8f;
                setTextureVertices(left, right, 1 - top, 1 - bottom);
                break;
            case 3:
                left = .6f;
                right = .8f;
                top = 1;
                bottom = .8f;
                setTextureVertices(left, right, 1 - top, 1 - bottom);
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

    public void analyzeAsteroidPosition(PointF playerLocation, float playerFacingAngle) {

        PointF worldLocation = getWorldLocation();

        Double angle;
        float distanceX = worldLocation.x - playerLocation.x;
        float distanceY = worldLocation.y - playerLocation.y;
        double distanceH = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if (distanceH < pixelsPerMeter * 2) {
            double sinValue = distanceY / distanceH;
            angle = (Math.acos(sinValue) * (180 / Math.PI));

            if (angle.isNaN()) {
                if (playerLocation.y > worldLocation.y) {
                    angle = 180.0;
                } else if (playerLocation.y < worldLocation.y) {
                    angle = 0.0;
                }
            }

            if (angle <= 45 || angle > 315) {
                if (playerFacingAngle == 360) {
                    setInFrontOfPlayer(true);
                } else {
                    setInFrontOfPlayer(false);
                }
            } else if (angle <= 135 && angle > 45) {
                if (playerLocation.x < worldLocation.x && playerFacingAngle == 270) {
                    setInFrontOfPlayer(true);
                } else if (playerLocation.x > worldLocation.x && playerFacingAngle == 90) {
                    setInFrontOfPlayer(true);
                } else {
                    setInFrontOfPlayer(false);
                }
            } else if (angle <= 225 && angle > 135) {
                if (playerFacingAngle == 180) {
                    setInFrontOfPlayer(true);
                } else {
                    setInFrontOfPlayer(false);
                }
            }
        } else {
            setInFrontOfPlayer(false);
        }
    }

    public PointF reposition(float objectFacingAngle, PointF objectLocation) {
        PointF newLocation = new PointF();

        if (objectFacingAngle == 180) {
            // Collision from above
            newLocation = new PointF(objectLocation.x, getWorldLocation().y + pixelsPerMeter);
        } else if (objectFacingAngle == 360) {
            // Collision from below
            newLocation = new PointF(objectLocation.x, getWorldLocation().y - pixelsPerMeter);
        } else if (objectFacingAngle == 90) {
            // Collision from the right
            newLocation = new PointF(getWorldLocation().x + pixelsPerMeter, objectLocation.y);
        } else if (objectFacingAngle == 270) {
            // Collision from the left
            newLocation = new PointF(getWorldLocation().x - pixelsPerMeter, objectLocation.y);
        }

        return newLocation;
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

    public boolean isBeingRedirected() {
        return beingRedirected;
    }

    public void setBeingRedirected(boolean beingRedirected) {
        this.beingRedirected = beingRedirected;
    }

    public boolean isInFrontOfPlayer() {
        return isInFrontOfPlayer;
    }

    private void setInFrontOfPlayer(boolean inFrontOfPlayer) {
        isInFrontOfPlayer = inFrontOfPlayer;
    }
}
