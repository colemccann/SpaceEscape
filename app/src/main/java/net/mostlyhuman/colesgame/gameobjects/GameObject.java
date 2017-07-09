package net.mostlyhuman.colesgame.gameobjects;

import android.content.Context;
import android.graphics.PointF;

import net.mostlyhuman.colesgame.game.ColorShaderProgram;
import net.mostlyhuman.colesgame.game.TextureShaderProgram;
import net.mostlyhuman.colesgame.helpers.CollisionPackage;
import net.mostlyhuman.colesgame.helpers.Constants;
import net.mostlyhuman.colesgame.helpers.TextureHelper;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;

import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.setRotateM;
import static android.opengl.Matrix.translateM;
import static net.mostlyhuman.colesgame.game.GLManager.BYTES_PER_FLOAT;
import static net.mostlyhuman.colesgame.game.GLManager.COMPONENTS_PER_TEXTURE;
import static net.mostlyhuman.colesgame.game.GLManager.COMPONENTS_PER_VERTEX;
import static net.mostlyhuman.colesgame.game.GLManager.STRIDE;

/**
 * Created by CaptainMcCann on 4/4/2017.
 */

public class GameObject {
    private String TAG = "GameObject";
    // If an object is destroyed, this will be set to false
    // and will not be drawn, updated, or checked for collision
    private boolean isActive;

    private Context context;

    private char type;

    private int numElements;
    private int numVertices;
    private int numTexElements;
    private int numTexVertices;
    private int mTexture;

    private float[] objectVertices;
    private float[] textureVertices;
    private FloatBuffer vertexBuffer;
    private FloatBuffer textureBuffer;

    // For translating each point from the object
    final float[] modelMatrix = new float[16];

    float[] viewportModelMatrix = new float[16];
    float[] rotateViewportModelMatrix = new float[16];

    private boolean isMoving;

    private float facingAngle = 90f;
    private float travelingAngle;

    private float xVelocity = 0f;
    private float yVelocity = 0f;
    private float speed = 0;
    private float maxSpeed = 0;

    private float height;
    private float width;

    private PointF worldLocation = new PointF();

    private CollisionPackage cp;

    public GameObject(Context context) {
        this.context = context;
        isActive = true;
    }

    public void update(float fps) {}

    public void setTextureResource(int textureResource) {
        mTexture = TextureHelper.loadTexture(context, textureResource);
    }

    public boolean isActive() {
        return isActive;
    }

    void setActive(boolean active) {
        isActive = active;
    }

    char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public PointF getWorldLocation() {
        return worldLocation;
    }

    public void setWorldLocation(float x, float y) {
        this.worldLocation.x = x;
        this.worldLocation.y = y;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public float getWidth() {
        return width;
    }

    public void setCollisionPackage(CollisionPackage cp) {
        this.cp = cp;
    }

    public CollisionPackage getCollisionPackage() {
        return cp;
    }

    void move(float fps) {
        if (fps > 60) {
            fps = 60;
        }

        if (xVelocity != 0) {
            worldLocation.x += xVelocity / fps;
        }

        if (yVelocity != 0) {
            worldLocation.y += yVelocity / fps;
        }
    }

    public void setVertices(float[] objectVertices) {

        this.objectVertices = new float[objectVertices.length];
        this.objectVertices = objectVertices;

        numElements = this.objectVertices.length;
        numVertices = numElements / COMPONENTS_PER_VERTEX;

        vertexBuffer = ByteBuffer.allocateDirect(
                numElements * BYTES_PER_FLOAT).order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        vertexBuffer.put(this.objectVertices).position(0);

    }

    public void setTextureVertices(float[] textureVertices) {

        this.textureVertices = new float[textureVertices.length];
        this.textureVertices = textureVertices;

        numTexElements = this.textureVertices.length;
        numTexVertices = numTexElements / COMPONENTS_PER_TEXTURE;

        textureBuffer = ByteBuffer.allocateDirect(numTexElements * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();

        textureBuffer.put(this.textureVertices).position(0);
    }

    public void setFacingAngle(float angle) {
        this.facingAngle = angle;
        if (getFacingAngle() > 360) {
            setFacingAngle(getFacingAngle() - 360);
        }
    }

    public float getFacingAngle() {
        return this.facingAngle;
    }

    public void draw(float[] viewportMatrix, TextureShaderProgram shaderProgram) {

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, mTexture);
        glUniform1i(shaderProgram.getTextureUniformLocation(), 0);

        glVertexAttribPointer(shaderProgram.getPositionAttributeLocation(), COMPONENTS_PER_VERTEX,
                GL_FLOAT, false, STRIDE, vertexBuffer);

        glVertexAttribPointer(shaderProgram.getTextureCoordinatesAttributeLocation(),
                COMPONENTS_PER_TEXTURE, GL_FLOAT, false, 0, textureBuffer);

        // For translating model coordinates into world coordinates
        setIdentityM(modelMatrix, 0);
        translateM(modelMatrix, 0, worldLocation.x, worldLocation.y, 0);

        // Combine the model matrix with the viewport matrix
        multiplyMM(viewportModelMatrix, 0, viewportMatrix, 0, modelMatrix, 0);

        // Rotate the object only
        setRotateM(modelMatrix, 0, facingAngle, 0, 0, 1.0f);

        // Multiply the rotation matrix into the model-viewport matrix
        multiplyMM(rotateViewportModelMatrix, 0, viewportModelMatrix, 0, modelMatrix, 0);

        // Give the matrix to openGL
        glUniformMatrix4fv(shaderProgram.getMVPMatrixLocation(), 1, false,
                rotateViewportModelMatrix, 0);

        glDrawArrays(GL_TRIANGLES, 0, numVertices);

    }

    public void draw(float[] viewportMatrix, ColorShaderProgram shaderProgram) {

        vertexBuffer.position(0);

        glVertexAttribPointer(shaderProgram.getPositionAttributeLocation(), COMPONENTS_PER_VERTEX,
                GL_FLOAT, false, STRIDE, vertexBuffer);

        setIdentityM(modelMatrix, 0);
        translateM(modelMatrix, 0, worldLocation.x, worldLocation.y, 0);

        multiplyMM(viewportModelMatrix, 0, viewportMatrix, 0, modelMatrix, 0);

        setRotateM(modelMatrix, 0, facingAngle, 0, 0, 1.0f);

        multiplyMM(rotateViewportModelMatrix, 0, viewportModelMatrix, 0, modelMatrix, 0);

        glUniformMatrix4fv(shaderProgram.getMVPMatrixLocation(), 1, false,
                rotateViewportModelMatrix, 0);

        glUniform4f(shaderProgram.getColorAttributeLocation(), 1.0f, 1.0f, 1.0f, 1.0f);

        switch (getType()) {
            case Constants.Types.BORDER:
                glDrawArrays(GL_LINES, 0, numVertices);
                break;
            case Constants.Types.STAR:
                glDrawArrays(GL_POINTS, 0, numVertices);
        }

    }

    // For Asteroids only
    public void redirect(float newTravelingAngle) {
        //// TODO: 5/29/2017 eliminate the need for this junk
    }

    //// TODO: 6/15/2017 move traveling angle stuff to Asteroid if possible
    public float getTravelingAngle() {
        return travelingAngle;
    }

    public void setTravelingAngle(float travelingAngle) {
        this.travelingAngle = travelingAngle;
        if (getTravelingAngle() > 360) {
            setTravelingAngle(getTravelingAngle() - 360);
        }
    }

    public void destroy() {
        setActive(false);
    }

}
