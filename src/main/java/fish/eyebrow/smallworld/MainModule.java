package fish.eyebrow.smallworld;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import fish.eyebrow.smallworld.io.CameraInputProcessor;
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
    private CameraInputProcessor cameraInputProcessor(@Named("camera.fieldOfView") final int fieldOfView,
                                                      @Named("camera.viewport.width") final float viewportWidth,
                                                      @Named("camera.viewport.height") final float viewportHeight,
                                                      @Named("camera.near") final int near,
                                                      @Named("camera.far") final int far) {
        return new CameraInputProcessor(fieldOfView, viewportWidth, viewportHeight, near, far);
    }


    @Inject
    @Provides
    @Singleton
    private SmallWorldApplication smallWorldApplication(final CameraInputProcessor cameraInputProcessor) {
        return new SmallWorldApplication(cameraInputProcessor);
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
