package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.Constants;

/**
 * Created by CaptainMcCann on 5/15/2017.
 */

public class TurretBase extends GameObject implements Turret.TurretContract {

    private int baseID;

    public TurretBase(Context context, float worldLocationX,
                      float worldLocationY, int pixelsPerMeter,
                      int baseID) {
        super(context);

        setBaseID(baseID);

        setType(Constants.Types.TURRET_BASE);
        setWorldLocation(worldLocationX, worldLocationY);

        setSize(pixelsPerMeter, pixelsPerMeter);
        float halfW = pixelsPerMeter / 2;
        float halfH = pixelsPerMeter / 2;

        float[] turretBaseVertices = new float[] {
                -halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, halfH, 0,
                -halfW, -halfH, 0,
                halfW, -halfH, 0,
                halfW, halfH, 0
        };
        setVertices(turretBaseVertices);

        float[] textureVertices = new float[] {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
        };
        setTextureVertices(textureVertices);

        setTextureResource(R.drawable.turretbase2);
    }

    @Override
    public void destroyBase(int id) {
        if (id == getBaseID()) {
            setActive(false);
        }
    }

    public int getBaseID() {
        return baseID;
    }

    public void setBaseID(int baseID) {
        this.baseID = baseID;
    }
}
