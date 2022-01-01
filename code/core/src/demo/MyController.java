package demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import controller.MainController;
import controller.OnLevelLoader;
import tools.Point;

public class MyController extends MainController {

    Point p = new Point(3, 3);

    @Override
    protected void setup() {
        camera.setFocusPoint(p);
        levelAPI.setOnLevelLoader(new MyLevelLoader());

        // alternative
        levelAPI.setOnLevelLoader(
                new OnLevelLoader() {
                    @Override
                    public void onLevelLoad() {
                        System.out.println("So geht es auch.");
                    }
                });

        levelAPI.loadLevel();
    }

    @Override
    protected void beginFrame() {
        Point newPosition = new Point(p);
        float movementSpeed = 1f;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) newPosition.y += movementSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) newPosition.y -= movementSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) newPosition.x += movementSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) newPosition.x -= movementSpeed;
        p = newPosition;
    }

    @Override
    protected void endFrame() {
        camera.setFocusPoint(p);
    }
}
