package fish.eyebrow.smallworld;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import fish.eyebrow.smallworld.io.CameraInputProcessor;

public class SmallWorldApplication extends ApplicationAdapter {

    private CameraInputProcessor cameraInputProcessor;

    private PerspectiveCamera camera;

    private ModelBatch modelBatch;

    private ModelInstance modelInstance;


    SmallWorldApplication(final CameraInputProcessor keyboardInputProcessor) {
        this.cameraInputProcessor = keyboardInputProcessor;
    }


    @Override
    public void create() {
        camera = cameraInputProcessor.getCamera();
        modelBatch = new ModelBatch();

        final ModelBuilder modelBuilder = new ModelBuilder();
        final Model box = modelBuilder.createBox(5, 5, 5,
                                                 new Material(ColorAttribute.createDiffuse(Color.RED)),
                                                 Usage.Position | Usage.Normal);
        modelInstance = new ModelInstance(box);

        Gdx.input.setCursorCatched(true);
        Gdx.input.setInputProcessor(cameraInputProcessor);
    }


    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_DEPTH_BUFFER_BIT | GL20.GL_COLOR_BUFFER_BIT);

        cameraInputProcessor.updateCamera();

        modelBatch.begin(camera);
        modelBatch.render(modelInstance);
        modelBatch.end();
    }
}
