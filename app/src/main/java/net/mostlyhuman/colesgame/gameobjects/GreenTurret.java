package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

import net.mostlyhuman.colesgame.game.SoundManager;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 8/6/2017.
 */

public class GreenTurret extends Turret {

    // Stays in place and shoots periodically

    private long lastShot;

    public GreenTurret(Context context, float worldLocationX,
                       float worldLocationY, int pixelsPerMeter,
                       float facingAngle, int turretID,
                       TurretContract turretContract,
                       LaserContract laser) {
        super(context, worldLocationX, worldLocationY, pixelsPerMeter,
                facingAngle, turretID, turretContract, laser);

        setType(Constants.Types.TURRET_GREEN);

        // Put actual texture vertices here
        float left = .4f;
        float right = .6f;
        float top = .4f;
        float bottom = .2f;

        setTextureVertices(left, right, 1 - top, 1 - bottom);
    }

    public void update() {
        // Fire once per second
        if (System.currentTimeMillis() > lastShot + 1000) {
            fire();
            lastShot = System.currentTimeMillis();
        }
    }
}
