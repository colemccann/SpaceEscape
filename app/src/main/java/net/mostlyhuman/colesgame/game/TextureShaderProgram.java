package net.mostlyhuman.colesgame.game;

import android.content.Context;

import net.mostlyhuman.colesgame.R;
import net.mostlyhuman.colesgame.helpers.Constants;

import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;

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

    }

    public void setTexture(int textureId) {
        glActiveTexture(textureId);
        glBindTexture(GL_TEXTURE_2D, textureId);
    }

    public void setUniforms(float[] matrix) {
        // Give the final matrix to OpenGL
        glUniformMatrix4fv(mMVPMatrixHandle, 1, false, matrix, 0);

        glUniform1i(mTextureUniformHandle, 0);
    }

    public int getMVPMatrixHandle() {
        return mMVPMatrixHandle;
    }

    public int getTextureUniformHandle() {
        return mTextureUniformHandle;
    }

    public int getPositionAttributeLocation() {
        return mPositionHandle;
    }

    public int getTextureCoordinatesAttributeLocation() {
        return mTextureCoordinateHandle;
    }
}
