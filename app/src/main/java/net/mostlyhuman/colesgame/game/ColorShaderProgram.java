package net.mostlyhuman.colesgame.game;

import android.content.Context;

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

    private final int mMatrixHandle;

    private final int mPositionHandle;
    private final int mColorHandle;

    public ColorShaderProgram(Context context) {
        super(context, R.raw.vertex_shader_color, R.raw.color_shader);

        mMatrixHandle = glGetUniformLocation(program, Constants.OpenGL.U_MATRIX);
        mPositionHandle = glGetAttribLocation(program, Constants.OpenGL.B_POSITION);
        mColorHandle = glGetAttribLocation(program, Constants.OpenGL.A_COLOR);

        glEnableVertexAttribArray(mPositionHandle);

    }

    @Override
    public void useProgram() {
        super.useProgram();

        //Log.d(TAG, "Using Color Program");
    }

    public int getMVPMatrixLocation() {
        return mMatrixHandle;
    }

    public int getPositionAttributeLocation() {
        return mPositionHandle;
    }

    public int getColorAttributeLocation() {
        return mColorHandle;
    }
}
