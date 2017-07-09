package net.mostlyhuman.colesgame.game;

import android.content.Context;
import android.util.Log;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.Constants;

import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;

/**
 * Created by CaptainMcCann on 7/8/2017.
 */

public class ColorShaderProgram extends ShaderProgram {

    private static final String TAG = "ColorShaderProgram";

    private final int mMVPMatrixHandle;

    private final int mPositionHandle;
    private final int mColorHandle;

    public ColorShaderProgram(Context context) {
        super(context, R.raw.vertex_shader_color, R.raw.color_shader);

        mMVPMatrixHandle = glGetUniformLocation(program, Constants.OpenGL.U_MVP_MATRIX);
        mPositionHandle = glGetAttribLocation(program, Constants.OpenGL.A_POSITION);
        mColorHandle = glGetAttribLocation(program, Constants.OpenGL.A_COLOR);

        glEnableVertexAttribArray(mPositionHandle);

    }

    @Override
    public void useProgram() {
        super.useProgram();

        //Log.d(TAG, "Using Color Program");
    }

    public int getMVPMatrixLocation() {
        return mMVPMatrixHandle;
    }

    public int getPositionAttributeLocation() {
        return mPositionHandle;
    }

    public int getColorAttributeLocation() {
        return mColorHandle;
    }
}
