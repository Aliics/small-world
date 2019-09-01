package fish.eyebrow.smallworld.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import fish.eyebrow.smallworld.SmallWorldApplication;
import fish.eyebrow.smallworld.io.provider.DirectionLabelTextProvider;
import fish.eyebrow.smallworld.io.provider.FPSTextLabelProvider;
import fish.eyebrow.smallworld.io.provider.KeysPressedLabelTextProvider;
import fish.eyebrow.smallworld.io.provider.PositionLabelTextProvider;

import java.util.HashSet;

public class DebugStage extends Stage {

    private Table table;

    private FPSTextLabelProvider fpsTextLabelProvider;

    private DirectionLabelTextProvider directionLabelTextProvider;

    private PositionLabelTextProvider positionLabelTextProvider;

    private KeysPressedLabelTextProvider keysPressedLabelTextProvider;

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
        final Label fpsValueLabel = new Label("0", labelStyle);
        final Label directionKeyLabel = new Label("[DIR]", labelStyle);
        final Label directionValueLabel = new Label("0", labelStyle);
        final Label positionKeyLabel = new Label("[POS]", labelStyle);
        final Label positionValueLabel = new Label("0", labelStyle);
        final Label keysPressedKeyLabel = new Label("[KPD]", labelStyle);
        final Label keysPressedValueLabel = new Label("0", labelStyle);

        addKeyValueRow(table, fpsKeyLabel, fpsValueLabel);
        addKeyValueRow(table, directionKeyLabel, directionValueLabel);
        addKeyValueRow(table, positionKeyLabel, positionValueLabel);
        addKeyValueRow(table, keysPressedKeyLabel, keysPressedValueLabel);

        fpsTextLabelProvider = new FPSTextLabelProvider(fpsValueLabel);
        directionLabelTextProvider = new DirectionLabelTextProvider(directionValueLabel);
        positionLabelTextProvider = new PositionLabelTextProvider(positionValueLabel);
        keysPressedLabelTextProvider = new KeysPressedLabelTextProvider(keysPressedValueLabel);
    }


    private void addKeyValueRow(final Table table, final Label keyLabel, final Label valueLabel) {
        table.top().left().add(keyLabel);
        table.add(valueLabel).top().left().padLeft(15).row();
    }


    public void draw(final SmallWorldApplication application) {
        super.draw();

        final PerspectiveCamera camera = application.getCamera();
        final HashSet<Integer> keysPressed = application.getFreeCameraController().getKeysPressed();

        fpsTextLabelProvider.provide();
        directionLabelTextProvider.provide(camera);
        positionLabelTextProvider.provide(camera);
        keysPressedLabelTextProvider.provide(keysPressed);
    }
}
