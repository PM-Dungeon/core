package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.DungeonCamera;
import tools.Constants;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class MainController extends ScreenAdapter {
    protected SpriteBatch batch;
    protected LevelController levelController;
    protected EntityController entityController;
    protected DungeonCamera camera;
    protected HUDController hud;
    protected GraphicController graphicController;

    private boolean doFirstFrame = true;

    // --------------------------- OWN IMPLEMENTATION ---------------------------
    protected void setup() {}

    protected void beginFrame() {}

    protected void endFrame() {}

    public void onLevelLoad() {}
    // --------------------------- END OWN IMPLEMENTATION ------------------------

    /**
     * Main Gameloop. Redraws the dungeon and calls all the update methods.
     *
     * @param delta Time since last loop. (since the PM-Dungeon is frame based, this isn't very
     *     useful)
     */
    @Override
    public final void render(float delta) {
        if (doFirstFrame) this.firstFrame();

        beginFrame();
        // clears the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        levelController.update();
        entityController.update();
        setupCamera();
        hud.update();
        endFrame();
    }

    private void firstFrame() {
        doFirstFrame = false;
        this.entityController = new EntityController();
        // the hud needs its own batch
        this.graphicController=new GraphicController();
        this.hud = new HUDController(new SpriteBatch());
        this.camera = new DungeonCamera(null, 0, 0);
        this.levelController = new LevelController(batch, graphicController);
        setup();
    }

    public void setSpriteBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    /** Setting up the camera. */
    private void setupCamera() {
        camera =
                new DungeonCamera(
                        null,
                        Constants.VIRTUALHEIGHT * Constants.WIDTH / (float) Constants.HEIGHT,
                        Constants.VIRTUALHEIGHT);
        camera.position.set(0, 0, 0);
        camera.zoom += 1;
        camera.update();
    }
}
