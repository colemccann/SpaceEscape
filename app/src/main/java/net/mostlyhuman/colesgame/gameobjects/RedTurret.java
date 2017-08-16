package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;

import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 8/6/2017.
 */

public class RedTurret extends Turret {

    // Follows player and shoots when player gets close enough
    // Perhaps increase laser speed

    public RedTurret(Context context, float worldLocationX,
                     float worldLocationY, int pixelsPerMeter,
                     float facingAngle, int turretID,
                     TurretContract turretContract,
                     LaserContract laser) {
        super(context, worldLocationX, worldLocationY, pixelsPerMeter,
                facingAngle, turretID, turretContract, laser);

        setType(Constants.Types.TURRET_RED);

        float left = .8f;
        float right = 1;
        float top = .4f;
        float bottom = .2f;

        setTextureVertices(left, right, 1 - top, 1 - bottom);
    }
}
