package net.mostlyhuman.colesgame.game;


import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;

/**
 * Created by CaptainMcCann on 4/4/2017.
 */

public class GLManager {

    public static final int COMPONENTS_PER_VERTEX = 3;
    public static final int COMPONENTS_PER_TEXTURE = 2;
    public static final int BYTES_PER_FLOAT = 4;
    public static final int STRIDE = (COMPONENTS_PER_VERTEX) * BYTES_PER_FLOAT;


    public static int mMVPMatrixHandle;
    public static int mPositionHandle;
    public static int mTextureUniformHandle;
    public static int mTextureCoordinateHandle;

    public static int GLProgram;

    private static String mVertexShaderCode;
    private static String mFragmentShaderCode;

    public static int getGLProgram() {
        return GLProgram;
    }

    public static int buildProgram() {
        return linkProgram(compileVertexShader(), compileFragmentShader());
    }

    public static void setVertexShaderCode(String mVertexShaderCode) {
        GLManager.mVertexShaderCode = mVertexShaderCode;
    }

    public static void setFragmentShaderCode(String mFragmentShaderCode) {
        GLManager.mFragmentShaderCode = mFragmentShaderCode;
    }

    private static String getVertexShaderCode() {
        return mVertexShaderCode;
    }

    private static String getFragmentShaderCode() {
        return mFragmentShaderCode;
    }

    private static int compileVertexShader() {
        return compileShader(GL_VERTEX_SHADER, getVertexShaderCode());
    }

    private static int compileFragmentShader() {
        return compileShader(GL_FRAGMENT_SHADER, getFragmentShaderCode());
    }


    private static int compileShader(int type, String shaderCode) {
        final int shader = glCreateShader(type);

        glShaderSource(shader, shaderCode);
        glCompileShader(shader);

        return shader;
    }

    private static int linkProgram(int vertexShader, int fragmentShader) {

        GLProgram = glCreateProgram();

        glAttachShader(GLProgram, vertexShader);
        glAttachShader(GLProgram, fragmentShader);

        glLinkProgram(GLProgram);

        return GLProgram;
    }
}