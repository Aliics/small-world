package fish.eyebrow.smallworld.io;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import org.lwjgl.util.vector.Vector3f;

public class CameraInputProcessor extends InputAdapter {

    private PerspectiveCamera camera;

    private Vector3f velocity;

    private float movementSpeed;


    public CameraInputProcessor(final int fieldOfView, final float viewportWidth, final float viewportHeight, final int near, final int far) {
        camera = new PerspectiveCamera();
        camera.position.set(Vector3.Zero);
        camera.fieldOfView = fieldOfView;
        camera.viewportWidth = viewportWidth;
        camera.viewportHeight = viewportHeight;
        camera.near = near;
        camera.far = far;

        velocity = new Vector3f();
        movementSpeed = 0.1F;
    }


    @Override
    public boolean keyDown(final int keycode) {
        switch (keycode) {
            case Movement.FORWARD:
                velocity.setZ(movementSpeed * -1F);
                break;
            case Movement.BACKWARD:
                velocity.setZ(movementSpeed * 1F);
                break;
        }

        switch (keycode) {
            case Movement.LEFT:
                velocity.setX(movementSpeed * -1F);
                break;
            case Movement.RIGHT:
                velocity.setX(movementSpeed * 1F);
                break;
        }

        return true;
    }


    @Override
    public boolean keyUp(final int keycode) {
        if (keycode == Movement.FORWARD || keycode == Movement.BACKWARD) {
            velocity.setZ(0F);
        }

        if (keycode == Movement.LEFT || keycode == Movement.RIGHT) {
            velocity.setX(0F);
        }

        return true;
    }


    public void updateCamera() {
        camera.position.add(velocity.x, velocity.y, velocity.z);
        camera.update();
    }


    public PerspectiveCamera getCamera() {
        return camera;
    }


    public Vector3f getVelocity() {
        return velocity;
    }
}
