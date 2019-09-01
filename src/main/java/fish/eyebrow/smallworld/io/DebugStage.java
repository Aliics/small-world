package fish.eyebrow.smallworld.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import fish.eyebrow.smallworld.SmallWorldApplication;

import java.util.HashSet;
import java.util.stream.Collectors;

public class DebugStage extends Stage {

    private Table table;

    private Label fpsValueLabel;

    private Label directionValueLabel;

    private Label positionValueLabel;

    private Label keysPressedValueLabel;

    private boolean debugTable;


    public DebugStage(final boolean debugTable) {
        super();

        this.debugTable = debugTable;

        createDebugTable();
        createDebugLabels();
        addActor(table);
    }


    private void createDebugTable() {
        table = new Table();
        table.setDebug(debugTable);
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    private void createDebugLabels() {
        final LabelStyle labelStyle = new LabelStyle(new BitmapFont(), Color.YELLOW);
        final Label fpsKeyLabel = new Label("[FPS]", labelStyle);
        final Label directionKeyLabel = new Label("[DIR]", labelStyle);
        final Label positionKeyLabel = new Label("[POS]", labelStyle);
        final Label keysPressedKeyLabel = new Label("[KPD]", labelStyle);
        fpsValueLabel = new Label("0", labelStyle);
        directionValueLabel = new Label("0", labelStyle);
        positionValueLabel = new Label("0", labelStyle);
        keysPressedValueLabel = new Label("0", labelStyle);

        addKeyValueRow(table, fpsKeyLabel, fpsValueLabel);
        addKeyValueRow(table, directionKeyLabel, directionValueLabel);
        addKeyValueRow(table, positionKeyLabel, positionValueLabel);
        addKeyValueRow(table, keysPressedKeyLabel, keysPressedValueLabel);
    }


    private void addKeyValueRow(final Table table, final Label keyLabel, final Label valueLabel) {
        table.top().left().add(keyLabel);
        table.add(valueLabel).top().left().padLeft(15).row();
    }


    public void draw(final SmallWorldApplication application) {
        super.draw();

        final PerspectiveCamera camera = application.getCamera();
        final HashSet<Integer> keysPressed = application.getFreeCameraController().getKeysPressed();

        fpsValueLabel.setText(Gdx.graphics.getFramesPerSecond());
        directionValueLabel.setText(String.format("X: %.2f, Y: %.2f, Z: %.2f", camera.direction.x, camera.direction.y, camera.direction.z));
        positionValueLabel.setText(String.format("X: %.2f, Y: %.2f, Z: %.2f", camera.position.x, camera.position.y, camera.position.z));
        keysPressedValueLabel.setText(keysPressed.stream().map(Keys::toString).collect(Collectors.joining(", ", "<", ">")));
    }
}
