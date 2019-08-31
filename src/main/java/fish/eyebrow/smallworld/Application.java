package fish.eyebrow.smallworld;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.google.inject.Guice;
import com.google.inject.Injector;
import fish.eyebrow.smallworld.guice.MainModule;

public class Application {

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new MainModule());
        injector.getInstance(LwjglApplication.class);
    }
}
