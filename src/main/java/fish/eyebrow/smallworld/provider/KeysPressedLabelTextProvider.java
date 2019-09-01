package fish.eyebrow.smallworld.provider;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.HashSet;
import java.util.stream.Collectors;

public class KeysPressedLabelTextProvider extends LabelTextProvider {

    private static final String DELIMITER = ", ";

    private static final String PREFIX = "<";

    private static final String SUFFIX = ">";


    public KeysPressedLabelTextProvider(final Label label) {
        super(label);
    }


    @Override
    @SuppressWarnings("unchecked")
    public void provide(final Object... args) {
        final HashSet<Integer> keysPressed = (HashSet<Integer>) args[0];

        label.setText(keysPressed.stream().map(Keys::toString).collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX)));
    }
}
