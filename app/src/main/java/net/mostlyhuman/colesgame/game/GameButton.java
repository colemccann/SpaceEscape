package net.mostlyhuman.colesgame.game;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

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

 class GameButton {

    private final String TAG = "GameButton";

    private final float[] viewportMatrix = new float[16];

    private int numVertices;

    private FloatBuffer vertexBuffer;
    private FloatBuffer textureBuffer;

    private int texture;

    GameButton(Context context, int textureResourceId, int top, int left,
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

        try {
            texture = TextureHelper.loadTexture(context, textureResourceId);
        } catch (RuntimeException e) {
            Log.e(TAG, e.getMessage());
        }

        float[] buttonTextureVertices = new float[] {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f
        };
        int numTexElements = buttonTextureVertices.length;

        textureBuffer = ByteBuffer.allocateDirect(numTexElements * GLManager.BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureBuffer.put(buttonTextureVertices);

        vertexBuffer.position(0);
        textureBuffer.position(0);
    }

    void draw(TextureShaderProgram shaderProgram) {

        shaderProgram.useProgram();

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture);
        glUniform1i(shaderProgram.getTextureUniformHandle(), 0);

        glVertexAttribPointer(shaderProgram.getPositionAttributeLocation(),
                GLManager.COMPONENTS_PER_VERTEX, GL_FLOAT, false, GLManager.STRIDE, vertexBuffer);

        glVertexAttribPointer(shaderProgram.getTextureCoordinatesAttributeLocation(),
                GLManager.COMPONENTS_PER_TEXTURE, GL_FLOAT, false, 0, textureBuffer);

        glUniformMatrix4fv(shaderProgram.getMVPMatrixHandle(), 1, false, viewportMatrix, 0);

        glDrawArrays(GL_TRIANGLES, 0, numVertices);
    }
}
