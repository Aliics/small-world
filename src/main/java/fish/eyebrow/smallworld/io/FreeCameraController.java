package fish.eyebrow.smallworld.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

import java.util.HashSet;

public class FreeCameraController extends InputAdapter {

    private PerspectiveCamera camera;

    private HashSet<Integer> keysPressed;

    private Vector3 placeholder;

    private float movementSpeed;


    public FreeCameraController(final int fieldOfView, final float viewportWidth, final float viewportHeight, final int near, final int far) {
        camera = new PerspectiveCamera();
        camera.position.set(Vector3.Zero);
        camera.fieldOfView = fieldOfView;
        camera.viewportWidth = viewportWidth;
        camera.viewportHeight = viewportHeight;
        camera.near = near;
        camera.far = far;

        keysPressed = new HashSet<>();
        placeholder = Vector3.Zero;
        movementSpeed = 0.1F;
    }


    @Override
    public boolean keyDown(final int keycode) {
        return keysPressed.add(keycode);
    }


    @Override
    public boolean keyUp(final int keycode) {
        return keysPressed.remove(keycode);
    }


    public void updateCamera() {
        processMouseInput();
        processKeyInput();
        camera.update(true);
    }


    private void processMouseInput() {
        placeholder.set(camera.direction).crs(camera.up).nor();
        camera.direction.rotate(camera.up, -Gdx.input.getDeltaX())
                        .rotate(-Gdx.input.getDeltaY(), placeholder.x, 0F, placeholder.z);
    }


    private void processKeyInput() {
        keysPressed.forEach(keycode -> {
            final float verticalMovement = keycode == Keys.W ? movementSpeed : keycode == Keys.S ? -movementSpeed : 0F;
            final float horizontalMovement = keycode == Keys.D ? movementSpeed : keycode == Keys.A ? -movementSpeed : 0F;
            if (keycode == Keys.W || keycode == Keys.S) {
                placeholder.set(camera.direction).nor().scl(verticalMovement);
            }
            else if (keycode == Keys.A || keycode == Keys.D) {
                placeholder.set(camera.direction).crs(camera.up).nor().scl(horizontalMovement);
            }
            else {
                placeholder.setZero();
            }

            camera.position.add(placeholder);
        });
    }


    public PerspectiveCamera getCamera() {
        return camera;
    }
}
