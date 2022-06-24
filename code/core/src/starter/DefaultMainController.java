package starter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import controller.MainController;
import controller.MenuController;
import menu.*;
import tools.Point;

public class DefaultMainController extends MainController {
    private int zoomLevel = 10;
    private MenuScreen menuScreen;

    /**
     * The program entry point to start the dungeon.
     *
     * @param args command line arguments, but not needed.
     */
    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new DefaultMainController());
    }

    @Override
    protected void setup() {
        // set the default generator
        // levelAPI.setGenerator(new RandomWalkGenerator());
        // load the first level
        levelAPI.loadLevel();

        // optional, uncomment this if you want a start menu:
        createMenuScreen();
        addDemoMenuEntries();
    }

    @Override
    protected void frame() {
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            zoomLevel++;
            camera.zoom = 0.05f * zoomLevel;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.O)) {
            zoomLevel--;
            if (zoomLevel <= 0) {
                zoomLevel = 1;
            }
            camera.zoom = 0.05f * zoomLevel;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.U)) {
            Vector3 position = camera.position;
            camera.setFocusPoint(new Point(position.x, position.y + 1));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.J)) {
            Vector3 position = camera.position;
            camera.setFocusPoint(new Point(position.x, position.y - 1));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.H)) {
            Vector3 position = camera.position;
            camera.setFocusPoint(new Point(position.x - 1, position.y));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            Vector3 position = camera.position;
            camera.setFocusPoint(new Point(position.x + 1, position.y));
        }
    }

    @Override
    public void onLevelLoad() {}

    private void createMenuScreen() {
        MenuController menuController = new MenuController();
        menuScreen = new MenuScreen();

        menuController.add(menuScreen);
        controller.add(menuController);
    }

    private void addDemoMenuEntries() {
        MenuScreenItemListener listener =
                new MenuScreenItemListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Gdx.app.log("CLICK", "TODO: One item was clicked.");
                    }
                };
        MenuScreenEntry demoEntry = new MenuScreenEntry("Demo 1");
        MenuScreenItem item1 = new MenuScreenItem("Item 1", listener);
        MenuScreenItem item2 = new MenuScreenItem("Item 2", listener);
        demoEntry.add(item1);
        demoEntry.add(item2);
        menuScreen.addMenuScreenEntry(demoEntry);
    }
}
