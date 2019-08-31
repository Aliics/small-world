package fish.eyebrow.smallworld.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CameraInputProcessorTestCase {

    private CameraInputProcessor cameraInputProcessor;

    private PerspectiveCamera camera;


    @BeforeEach
    void setUp() {
        cameraInputProcessor = new CameraInputProcessor(90, 100, 100, 0, 100);
        camera = cameraInputProcessor.getCamera();
    }


    @Test
    void forwardMovement() {
        cameraInputProcessor.keyDown(Keys.W);

        Assertions.assertThat(cameraInputProcessor.getVelocity().z).isNotZero().isNegative();

        cameraInputProcessor.keyUp(Keys.W);

        Assertions.assertThat(cameraInputProcessor.getVelocity().z).isZero();
    }


    @Test
    void backwardMovement() {
        cameraInputProcessor.keyDown(Keys.S);

        Assertions.assertThat(cameraInputProcessor.getVelocity().z).isNotZero().isPositive();

        cameraInputProcessor.keyUp(Keys.S);

        Assertions.assertThat(cameraInputProcessor.getVelocity().z).isZero();
    }


    @Test
    void leftMovement() {
        cameraInputProcessor.keyDown(Keys.A);

        Assertions.assertThat(cameraInputProcessor.getVelocity().x).isNotZero().isNegative();

        cameraInputProcessor.keyUp(Keys.A);

        Assertions.assertThat(cameraInputProcessor.getVelocity().x).isZero();
    }


    @Test
    void rightMovement() {
        cameraInputProcessor.keyDown(Keys.D);

        Assertions.assertThat(cameraInputProcessor.getVelocity().x).isNotZero().isPositive();

        cameraInputProcessor.keyUp(Keys.D);

        Assertions.assertThat(cameraInputProcessor.getVelocity().x).isZero();
    }
}
