package fish.eyebrow.smallworld.io.controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import fish.eyebrow.smallworld.io.stage.DebugStage;

import java.util.HashSet;
import java.util.Objects;

public class BaseInputController extends InputAdapter {

    private final HashSet<Integer> keysPressed = new HashSet<>();

    private DebugStage debugStage;


    @Override
    public boolean keyDown(final int keycode) {
        if (keycode == Keys.F1 && Objects.nonNull(debugStage)) {
            debugStage.setDebugVisible(!debugStage.getDebugVisible());
        }

        return keysPressed.add(keycode);
    }


    @Override
    public boolean keyUp(final int keycode) {
        return keysPressed.remove(keycode);
    }


    public void setDebugStage(final DebugStage debugStage) {
        this.debugStage = debugStage;
    }


    public HashSet<Integer> getKeysPressed() {
        return keysPressed;
    }
}
