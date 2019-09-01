package fish.eyebrow.smallworld.guice;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import fish.eyebrow.smallworld.SmallWorldApplication;
import fish.eyebrow.smallworld.io.controller.FreeCameraController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class MainModule extends AbstractModule {

    private static final Logger logger = LoggerFactory.getLogger(MainModule.class);


    @Override
    protected void configure() {
        try {
            final Properties configurationProperties = new Properties();
            final InputStream configurationStream = ClassLoader.getSystemResourceAsStream("configuration.properties");
            configurationProperties.load(configurationStream);

            Names.bindProperties(binder(), configurationProperties);
        }
        catch (final Exception e) {
            logger.warn("Problem arose when binding names from configuration.properties: {}", e.getMessage());
        }
    }


    @Provides
    @Singleton
    private FreeCameraController freeCameraController(@Named("camera.fieldOfView") final int fieldOfView,
                                                      @Named("camera.viewport.width") final float viewportWidth,
                                                      @Named("camera.viewport.height") final float viewportHeight,
                                                      @Named("camera.near") final int near,
                                                      @Named("camera.far") final int far) {
        return new FreeCameraController(fieldOfView, viewportWidth, viewportHeight, near, far);
    }


    @Inject
    @Provides
    @Singleton
    private SmallWorldApplication smallWorldApplication(final FreeCameraController freeCameraController,
                                                        @Named("debug.stage.table.debug") final boolean debugStageTableDebug) {
        return new SmallWorldApplication(freeCameraController, debugStageTableDebug);
    }


    @Inject
    @Provides
    @Singleton
    private LwjglApplication lwjglApplication(@Named("window.title") final String title,
                                              @Named("window.width") final int width,
                                              @Named("window.height") final int height,
                                              final SmallWorldApplication smallWorldApplication) {
        final LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title = title;
        configuration.width = width;
        configuration.height = height;

        return new LwjglApplication(smallWorldApplication, configuration);
    }
}
