package fish.eyebrow.smallworld;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import fish.eyebrow.smallworld.io.FreeCameraController;

import java.util.HashSet;
import java.util.Set;

public class SmallWorldApplication extends ApplicationAdapter {

    private FreeCameraController freeCameraController;

    private PerspectiveCamera camera;

    private ModelBatch modelBatch;

    private Set<ModelInstance> modelInstances;


    public SmallWorldApplication(final FreeCameraController freeCameraController) {
        this.freeCameraController = freeCameraController;
    }


    @Override
    public void create() {
        camera = freeCameraController.getCamera();
        modelBatch = new ModelBatch();
        modelInstances = new HashSet<>();

        Gdx.input.setCursorCatched(true);
        Gdx.input.setInputProcessor(freeCameraController);

        Gdx.gl.glClearColor(0.5F, 0.8F, 0.95F, 0F);
    }


    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_COLOR_BUFFER_BIT);

        freeCameraController.updateCamera();

        modelBatch.begin(camera);
        modelBatch.render(modelInstances);
        modelBatch.end();
    }
}
