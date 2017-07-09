package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

import net.mostlyhuman.colesgame.helpers.Constants;

import java.util.Random;

/**
 * Created by CaptainMcCann on 7/8/2017.
 */

public class Star extends GameObject {

    Random r;

    public Star(Context context, int mapWidth, int mapHeight)
    {super(context);
        setType(Constants.Types.STAR);

        r = new Random();
        setWorldLocation(r.nextInt(mapWidth), r.nextInt(mapHeight));

        // Define the star as a single point in exactly the coordinates as its world location
        float[] starVertices = new float[] {0, 0, 0};

        setVertices(starVertices);
    }

    public void update() {
        // Randomly twinkle the stars
        int n = r.nextInt(1000);
        if (n == 0) {
            // Switch on or off
            if (isActive()) {
                setActive(false);
            } else {
                setActive(true);
            }
        }
    }

}
