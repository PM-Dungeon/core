package client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import controller.GameSetup;
import controller.MainController;

public final class HtmlLauncher extends GwtApplication {
    @Override
    public GwtApplicationConfiguration getConfig() {
        // Resizable application, uses available space in browser
        // return new GwtApplicationConfiguration(true);
        // Fixed size application:
        GwtApplicationConfiguration config = new GwtApplicationConfiguration(640, 480);
        config.disableAudio = true;
        return config;
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new GameSetup(new MainController());
    }
}
