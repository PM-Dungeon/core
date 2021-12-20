package client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.MainController;
import demo.MyController;
import tools.Constants;

public final class HtmlLauncher extends GwtApplication {
    @Override
    public GwtApplicationConfiguration getConfig() {
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
        return new Game() {
            private final MainController mc = new MyController();
            /**
             * The batch is necessary to draw ALL the stuff. Every object that uses draw need to
             * know the batch.
             */
            private SpriteBatch batch;

            @Override
            public void create() {
                batch = new SpriteBatch();
                mc.setSpriteBatch(batch);
                setScreen(mc);
            }

            @Override
            public void dispose() {
                batch.dispose();
            }
        };
    }
}
