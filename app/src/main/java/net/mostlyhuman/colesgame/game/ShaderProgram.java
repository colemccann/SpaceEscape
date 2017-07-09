package net.mostlyhuman.colesgame.game;

import android.content.Context;

import net.mostlyhuman.colesgame.helpers.RawResourceReader;

import static android.opengl.GLES20.glUseProgram;

/**
 * Created by CaptainMcCann on 7/8/2017.
 */

public class ShaderProgram {

    final int program;

    protected ShaderProgram(Context context, int vertexShaderResourceID,
                            int fragmentShaderResourceID) {
        program = GLManager.buildProgram(
                RawResourceReader.readTextFileFromRawResource(context, vertexShaderResourceID),
                RawResourceReader.readTextFileFromRawResource(context, fragmentShaderResourceID));
    }

    public void useProgram() {
        glUseProgram(program);
    }
}
