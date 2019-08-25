package fish.eyebrow.smallworld;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraInputProcessor extends InputAdapter {

    private PerspectiveCamera camera;


    CameraInputProcessor(final int fieldOfView, final float viewportWidth, final float viewportHeight, final int near, final int far) {
        camera = new PerspectiveCamera();
        camera.fieldOfView = fieldOfView;
        camera.viewportWidth = viewportWidth;
        camera.viewportHeight = viewportHeight;
        camera.near = near;
        camera.far = far;
    }


    @Override
    public boolean keyDown(final int keycode) {
        switch (keycode) {
            case Keys.W:
                camera.position.add(new Vector3(0, 0, -1));
                break;
            case Keys.A:
                camera.position.add(new Vector3(-1, 0, 0));
                break;
            case Keys.S:
                camera.position.add(new Vector3(0, 0, 1));
                break;
            case Keys.D:
                camera.position.add(new Vector3(1, 0, 0));
                break;
        }

        return true;
    }


    PerspectiveCamera getCamera() {
        return camera;
    }
}
