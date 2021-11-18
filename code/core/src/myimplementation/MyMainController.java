package myimplementation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.*;
import graphic.DungeonCamera;
import graphic.HUDCamera;
import tools.Constants;

public class MyMainController extends MainController {
    Hero hero;

    @Override
    protected void setup() {
        this.entityController = new EntityController();
        this.camera =
                new DungeonCamera(
                        null,
                        Constants.VIRTUALHEIGHT * Constants.WIDTH / (float) Constants.HEIGHT,
                        Constants.VIRTUALHEIGHT);
        this.camera.position.set(0, 0, 0);
        this.camera.zoom += 1;
        this.graphicController = new GraphicController(camera);
        this.hud = new HUDController(new SpriteBatch(), graphicController, new HUDCamera());
        this.levelController = new LevelController(batch, graphicController);

        hero = new Hero(batch, graphicController, camera);

        entityController.addEntity(hero);
    }

    @Override
    protected void beginFrame() {
        super.beginFrame();
    }

    @Override
    protected void endFrame() {
        super.endFrame();
    }

    @Override
    public void onLevelLoad() {
        super.onLevelLoad();
    }

    @Override
    public void setSpriteBatch(SpriteBatch batch) {
        super.setSpriteBatch(batch);
    }
}
