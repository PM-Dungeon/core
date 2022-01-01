package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.DungeonCamera;
import graphic.HUDCamera;
import graphic.Painter;
import level.LevelAPI;
import level.generator.IGenerator;
import level.generator.dummy.DummyGenerator;
import level.generator.dungeong.levelg.LevelG;
import tools.Constants;

import java.lang.reflect.Method;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

/** The heart of the framework. From here all strings are pulled. */
public class MainController extends ScreenAdapter {
    protected SpriteBatch batch;
    protected SpriteBatch hudBatch;
    protected HUDCamera hudCamera;
    protected LevelAPI levelAPI;
    protected EntityController entityController;
    protected DungeonCamera camera;
    protected HUDController hud;
    protected Painter painter;
    protected IGenerator generator;

    private boolean doFirstFrame = true;

    // --------------------------- OWN IMPLEMENTATION ---------------------------
    protected void setup() {}

    protected void beginFrame() {}

    protected void endFrame() {}

    protected void onLevelLoad() {}
    // --------------------------- END OWN IMPLEMENTATION ------------------------

    /**
     * Main game loop. Redraws the dungeon and calls the own implementation (beginFrame, endFrame
     * and onLevelLoad).
     *
     * @param delta Time since last loop.
     */
    @Override
    public final void render(float delta) {
        if (doFirstFrame) {
            firstFrame();
        }

        // clears the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        beginFrame();
        levelAPI.update();
        entityController.update();
        camera.update();
        hud.update();
        endFrame();
    }

    private void firstFrame() {
        doFirstFrame = false;
        entityController = new EntityController();
        setupCamera();
        painter = new Painter(camera);
        hudBatch = new SpriteBatch();
        hudCamera = new HUDCamera();
        hud = new HUDController(hudBatch, hudCamera);
        if (Constants.USE_DUMMY_GENERATOR) generator = new DummyGenerator();
        else generator = new LevelG();
        setupLevelAPI(batch, painter, generator);
        setup();
    }

    public void setSpriteBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    /** Setting up the camera. */
    private void setupCamera() {
        camera = new DungeonCamera(null, Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        camera.zoom = Constants.DEFAULT_ZOOM_FACTOR;

        // See also:
        // https://stackoverflow.com/questions/52011592/libgdx-set-ortho-camera
    }

    private void setupLevelAPI(SpriteBatch batch, Painter painter, IGenerator generator) {
        try {
            // this method will be called every time a new level gets load
            Method functionToPass = this.getClass().getMethod("onLevelLoad");
            // if you need parameter four your method, add them here
            Object[] arguments = new Object[0];
            levelAPI = new LevelAPI(batch, painter, generator, functionToPass, this, arguments);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
