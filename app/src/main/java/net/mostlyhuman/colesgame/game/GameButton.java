package net.mostlyhuman.colesgame.game;

import android.content.Context;
import android.graphics.PointF;

import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.helpers.TextureHelper;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.Matrix.orthoM;

/**
 * Created by CaptainMcCann on 4/13/2017.
 */

public class GameButton {

    private final float[] viewportMatrix = new float[16];

    private static int glProgram;

    private int numVertices;
    private int numTexVertices;

    private int uMVPMatrixHandle;
    private int aPositionHandle;
    private int uTextureHandle;
    private int vTextureCoordinateHandle;

    private FloatBuffer vertexBuffer;
    private FloatBuffer textureBuffer;

    private int texture;

    public GameButton(Context context, int textureResourceId, int top, int left,
                      int bottom, int right, GameManager gameManager) {
        orthoM(viewportMatrix, 0, 0, gameManager.screenWidth, gameManager.screenHeight, 0, 0, 1f);

        PointF p1 = new PointF();
        p1.x = left;
        p1.y = top;

        PointF p2 = new PointF();
        p2.x = left;
        p2.y = bottom;

        PointF p3 = new PointF();
        p3.x = right;
        p3.y = top;

        PointF p4 = new PointF();
        p4.x = right;
        p4.y = bottom;

        float[] buttonVertexVertices = new float[]{
                p1.x, p1.y, 0,
                p2.x, p2.y, 0,
                p3.x, p3.y, 0,

                p2.x, p2.y, 0,
                p4.x, p4.y, 0,
                p3.x, p3.y, 0,


        };

        int numElements = buttonVertexVertices.length;
        numVertices = numElements / GLManager.COMPONENTS_PER_VERTEX;
        vertexBuffer = ByteBuffer.allocateDirect(numElements * GLManager.BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexBuffer.put(buttonVertexVertices);

        texture = TextureHelper.loadTexture(context, textureResourceId);
        float[] buttonTextureVertices = new float[] {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
        };
        int numTexElements = buttonTextureVertices.length;
        numTexVertices = numTexElements / GLManager.COMPONENTS_PER_TEXTURE;
        textureBuffer = ByteBuffer.allocateDirect(numTexElements * GLManager.BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureBuffer.put(buttonTextureVertices);

        glProgram = GLManager.getGLProgram();

        uMVPMatrixHandle = glGetUniformLocation(glProgram, Constants.OpenGL.U_MVP_MATRIX);
        aPositionHandle = glGetAttribLocation(glProgram, Constants.OpenGL.A_POSITION);
        uTextureHandle = glGetUniformLocation(glProgram, Constants.OpenGL.U_TEXTURE);
        vTextureCoordinateHandle = glGetAttribLocation
                (glProgram, Constants.OpenGL.A_TEX_COORDINATE);

        glEnableVertexAttribArray(aPositionHandle);
        glEnableVertexAttribArray(vTextureCoordinateHandle);

        vertexBuffer.position(0);
        textureBuffer.position(0);
    }

    public void draw() {
        glUseProgram(glProgram);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture);
        glUniform1i(uTextureHandle, 0);

        glVertexAttribPointer(aPositionHandle, GLManager.COMPONENTS_PER_VERTEX,
                GL_FLOAT, false, GLManager.STRIDE, vertexBuffer);

        glVertexAttribPointer(vTextureCoordinateHandle, GLManager.COMPONENTS_PER_TEXTURE,
                GL_FLOAT, false, 0, textureBuffer);

        glUniformMatrix4fv(uMVPMatrixHandle, 1, false, viewportMatrix, 0);

        glDrawArrays(GL_TRIANGLES, 0, numVertices);
    }
}
