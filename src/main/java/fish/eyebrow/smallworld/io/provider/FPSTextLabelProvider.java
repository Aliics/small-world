package fish.eyebrow.smallworld.io.provider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class FPSTextLabelProvider extends LabelTextProvider {

    public FPSTextLabelProvider(final Label label) {
        super(label);
    }


    @Override
    public void provide(final Object... args) {
        label.setText(Gdx.graphics.getFramesPerSecond());
    }
}
