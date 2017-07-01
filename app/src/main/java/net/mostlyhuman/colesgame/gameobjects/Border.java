package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;

import net.mostlyhuman.colesgame.helpers.Constants;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.setRotateM;
import static android.opengl.Matrix.translateM;
import static net.mostlyhuman.colesgame.game.GLManager.COMPONENTS_PER_VERTEX;
import static net.mostlyhuman.colesgame.game.GLManager.STRIDE;
import static net.mostlyhuman.colesgame.game.GLManager.mMVPMatrixHandle;
import static net.mostlyhuman.colesgame.game.GLManager.mPositionHandle;

/**
 * Created by CaptainMcCann on 6/30/2017.
 */

public class Border extends GameObject {

    public Border(Context context, float mapWidth, float mapHeight) {
        super(context);

        setType(Constants.Types.BORDER);

        setWorldLocation(mapWidth / 2, mapHeight / 2);

        setSize(mapWidth, mapHeight);

        float[] borderVertices = new float[] {
                -mapWidth / 2, -mapHeight / 2, 0,
                mapWidth / 2, -mapHeight / 2, 0,
                mapWidth / 2, - mapHeight / 2, 0,
                mapWidth / 2, mapHeight / 2, 0,
                mapWidth / 2, mapHeight / 2, 0,
                -mapWidth / 2, mapHeight / 2, 0,
                -mapWidth / 2, -mapHeight / 2, 0
        };

        setVertices(borderVertices);
    }

    @Override
    public void draw(float[] viewportMatrix) {
        glUseProgram(getGlProgram());

        glVertexAttribPointer(mPositionHandle,
                COMPONENTS_PER_VERTEX, GL_FLOAT,
                false, STRIDE, getVertexBuffer());

        glEnableVertexAttribArray(mPositionHandle);

        setIdentityM(modelMatrix, 0);

        translateM(modelMatrix, 0, getWorldLocation().x, getWorldLocation().y, 0);

        multiplyMM(viewportModelMatrix, 0, viewportMatrix, 0, modelMatrix, 0);

        setRotateM(modelMatrix, 0, getFacingAngle(), 0, 0, 1.0f);

        multiplyMM(rotateViewportModelMatrix, 0, viewportModelMatrix, 0, modelMatrix, 0);

        glUniformMatrix4fv(mMVPMatrixHandle, 1, false, rotateViewportModelMatrix, 0);

    }
}
