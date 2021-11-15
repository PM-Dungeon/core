import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.GameSetup;
import controller.MainController;

public final class DesktopLauncher {
    public static void run(MainController mc) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 640;
        config.height = 480;
        config.foregroundFPS = 30;
        new LwjglApplication(new GameSetup(mc),config);
    }

    public static void main(String[]args){
        DesktopLauncher.run(new MainController());
    }
}
