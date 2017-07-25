package net.mostlyhuman.colesgame.game;



import android.util.Log;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_TRUE;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderInfoLog;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;

/**
 * Created by CaptainMcCann on 4/4/2017.
 */

public class GLManager {

    private static final String TAG = "GLManager";

    public static final int COMPONENTS_PER_VERTEX = 3;
    public static final int COMPONENTS_PER_TEXTURE = 2;

    // For use with ColorShaderProgram
    public static final int POSITION_COMPONENT_COUNT = 2;
    public static final int COLOR_COMPONENT_COUNT = 3;
    public static final int COLOR_STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * 4;

    public static final int BYTES_PER_FLOAT = 4;
    public static final int STRIDE = (COMPONENTS_PER_VERTEX) * BYTES_PER_FLOAT;


    private static int compileVertexShader(String vertexShaderSource) {
        return compileShader(GL_VERTEX_SHADER, vertexShaderSource);
    }

    private static int compileFragmentShader(String fragmentShaderSource) {
        return compileShader(GL_FRAGMENT_SHADER, fragmentShaderSource);
    }

    private static int compileShader(int type, String shaderCode) {
        final int shaderObjectId = glCreateShader(type);

        if (shaderObjectId == 0) {
            Log.d(TAG, "Could not create new Shader.");
        }

        glShaderSource(shaderObjectId, shaderCode);
        glCompileShader(shaderObjectId);

        final int[] compileStatus = new int[1];
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);
        Log.d(TAG, "Results of compiling source: " + "\n" + shaderCode + "\n:" + glGetShaderInfoLog(shaderObjectId));

        if (compileStatus[0] == 0) {
            Log.d(TAG, "Compilation of shader failed.");
        }

        return shaderObjectId;
    }

    private static int linkProgram(int vertexShader, int fragmentShader) {
        final int programObjectId = glCreateProgram();

        glAttachShader(programObjectId, vertexShader);
        glAttachShader(programObjectId, fragmentShader);

        glLinkProgram(programObjectId);

        return programObjectId;
    }

    static int buildProgram(String vertexShaderSource, String fragmentShaderSource) {
        int program;
        int vertexShader = compileVertexShader(vertexShaderSource);
        int fragmentShader = compileFragmentShader(fragmentShaderSource);

        program = linkProgram(vertexShader, fragmentShader);

        int[] linkStatus = new int[1];
        glGetProgramiv(program, GL_LINK_STATUS, linkStatus, 0);

        boolean b = linkStatus[0] == GL_TRUE;
        Log.d(TAG, "Link status: " + program + ": " + b);

        return program;
    }
}