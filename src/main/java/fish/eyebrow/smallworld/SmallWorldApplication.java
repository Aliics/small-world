package fish.eyebrow.smallworld;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import fish.eyebrow.smallworld.io.stage.DebugStage;
import fish.eyebrow.smallworld.io.controller.FreeCameraController;

import java.util.HashSet;

public class SmallWorldApplication extends ApplicationAdapter {

    private final HashSet<ModelInstance> modelInstances = new HashSet<>();

    private final FreeCameraController freeCameraController;

    private final boolean debugStageTableDebug;

    private PerspectiveCamera camera;

    private ModelBatch modelBatch;

    private DebugStage debugStage;


    public SmallWorldApplication(final FreeCameraController freeCameraController, final boolean debugStageTableDebug) {
        this.freeCameraController = freeCameraController;
        this.debugStageTableDebug = debugStageTableDebug;
    }


    @Override
    public void create() {
        camera = freeCameraController.getCamera();
        modelBatch = new ModelBatch();
        debugStage = new DebugStage(debugStageTableDebug);

        freeCameraController.setDebugStage(debugStage);
        Gdx.input.setInputProcessor(freeCameraController);

        Gdx.gl.glClearColor(0.5F, 0.8F, 0.95F, 0F);
    }


    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_COLOR_BUFFER_BIT);

        freeCameraController.updateCamera();

        debugStage.draw(this);

        modelBatch.begin(camera);
        modelBatch.render(modelInstances);
        modelBatch.end();
    }


    public FreeCameraController getFreeCameraController() {
        return freeCameraController;
    }


    public PerspectiveCamera getCamera() {
        return camera;
    }
}
