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

public class TextureShaderProgram extends ShaderProgram {

    private final int mMVPMatrixHandle;
    private final int mTextureUniformHandle;

    private final int mPositionHandle;
    private final int mTextureCoordinateHandle;

    public TextureShaderProgram(Context context) {
        super(context, R.raw.vertex_shader_texture, R.raw.texture_shader);

        mMVPMatrixHandle = glGetUniformLocation(program, Constants.OpenGL.U_MVP_MATRIX);
        mTextureUniformHandle = glGetUniformLocation(program, Constants.OpenGL.U_TEXTURE);
        mPositionHandle = glGetAttribLocation(program, Constants.OpenGL.A_POSITION);
        mTextureCoordinateHandle = glGetAttribLocation(program, Constants.OpenGL.A_TEX_COORDINATE);

        glEnableVertexAttribArray(mPositionHandle);
        glEnableVertexAttribArray(mTextureCoordinateHandle);
    }

    public int getMVPMatrixLocation() {
        return mMVPMatrixHandle;
    }

    public int getTextureUniformLocation() {
        return mTextureUniformHandle;
    }

    public int getPositionAttributeLocation() {
        return mPositionHandle;
    }

    public int getTextureCoordinatesAttributeLocation() {
        return mTextureCoordinateHandle;
    }
}
