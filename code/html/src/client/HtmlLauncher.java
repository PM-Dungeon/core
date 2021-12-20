package client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import controller.LibgdxSetup;
import demo.MyController;
import tools.Constants;

public final class HtmlLauncher extends GwtApplication {
    @Override
    public GwtApplicationConfiguration getConfig() {
        // Gdx.app.setLogLevel(Application.LOG_DEBUG);

        // Resizable application, uses available space in browser
        // return new GwtApplicationConfiguration(true);
        // Fixed size application:
        GwtApplicationConfiguration config =
                new GwtApplicationConfiguration(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        config.disableAudio = true;
        return config;
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new LibgdxSetup(new MyController());
    }
}
