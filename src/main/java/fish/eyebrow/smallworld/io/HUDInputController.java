package fish.eyebrow.smallworld.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class HUDInputController extends InputAdapter {

    private final DebugStage debugStage;


    public HUDInputController(final DebugStage debugStage) {
        this.debugStage = debugStage;
    }


    @Override
    public boolean keyDown(final int keycode) {
        if (keycode == Keys.F1) {
            debugStage.setDebugVisible(!debugStage.getDebugVisible());
        }
        return true;
    }
}
