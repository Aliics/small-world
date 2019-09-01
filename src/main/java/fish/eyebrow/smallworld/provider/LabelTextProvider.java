package fish.eyebrow.smallworld.provider;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

abstract class LabelTextProvider {

    Label label;


    LabelTextProvider(final Label label) {
        this.label = label;
    }


    abstract void provide(final Object... args);
}
