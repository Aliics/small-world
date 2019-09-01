package fish.eyebrow.smallworld.io.provider;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class PositionLabelTextProvider extends LabelTextProvider {

    private static final String COORDINATE_FORMAT = "X: %.2f, Y: %.2f, Z: %.2f";


    public PositionLabelTextProvider(final Label label) {
        super(label);
    }


    @Override
    public void provide(final Object... args) {
        final PerspectiveCamera camera = (PerspectiveCamera) args[0];
        final Vector3 position = camera.position;

        label.setText(String.format(COORDINATE_FORMAT, position.x, position.y, position.z));
    }
}
