package starter;

import basiselements.hud.ScreenButton;
import basiselements.hud.ScreenImage;
import basiselements.hud.ScreenText;
import basiselements.hud.TextButtonListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import controller.MainController;
import controller.ScreenController;
import tools.Point;

public class DefaultMainController extends MainController {
    private int zoomLevel = 10;

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
        createBetterMenu();
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

    ScreenButton button;

    private void createBetterMenu() {
        ScreenController screenController = new ScreenController(hudBatch);
        screenController.add(new ScreenImage("assets/hud/ui_heart_half.png", new Point(1, 1)));
        button =
                new ScreenButton(
                        "Dies ist der Text",
                        new Point(100, 100),
                        new TextButtonListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                System.out.println("Hey ich wurde gedrückt!");
                            }
                        });
        screenController.add(button);
        screenController.add(ScreenText.build("blub", new Point(100, 0), 2));

        controller.add(screenController);
    }
}
