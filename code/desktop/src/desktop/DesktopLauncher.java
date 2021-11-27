package desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import controller.GameSetup;
import controller.MainController;
import controller.MainController2;
import tools.Constants;

public final class DesktopLauncher {
    public static void run(MainController mc) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Constants.WIDTH;
        config.height = Constants.HEIGHT;
        config.foregroundFPS = Constants.FRAME_RATE;
        new LwjglApplication(new GameSetup(mc), config);
    }

    public static void main(String[] args) {
        DesktopLauncher.run(new MainController2());
    }
}
