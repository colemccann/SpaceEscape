package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;

import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 8/6/2017.
 */

public class BlueTurret extends Turret {

    // Stationary and shoots when player crosses firing path


    public BlueTurret(Context context, float worldLocationX,
                      float worldLocationY, int pixelsPerMeter,
                      float facingAngle, int turretID,
                      TurretContract turretContract,
                      LaserContract laser) {

        super(context, worldLocationX, worldLocationY, pixelsPerMeter,
                facingAngle, turretID, turretContract, laser);

        setType(Constants.Types.TURRET_BLUE);

        float left = .6f;
        float right = .8f;
        float top = .4f;
        float bottom = .2f;

        setTextureVertices(left, right, 1 - top, 1 - bottom);
    }
}
