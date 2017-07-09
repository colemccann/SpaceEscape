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

    private static final String TAG = "GLManager";

    public static final int COMPONENTS_PER_VERTEX = 3;
    public static final int COMPONENTS_PER_TEXTURE = 2;
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

        glShaderSource(shaderObjectId, shaderCode);
        glCompileShader(shaderObjectId);

        return shaderObjectId;
    }

    private static int linkProgram(int vertexShader, int fragmentShader) {
        final int programObjectId = glCreateProgram();

        glAttachShader(programObjectId, vertexShader);
        glAttachShader(programObjectId, fragmentShader);

        glLinkProgram(programObjectId);

        return programObjectId;
    }

    public static int buildProgram(String vertexShaderSource, String fragmentShaderSource) {
        int program;
        int vertexShader = compileVertexShader(vertexShaderSource);
        int fragmentShader = compileFragmentShader(fragmentShaderSource);

        program = linkProgram(vertexShader, fragmentShader);

        return program;
    }
}