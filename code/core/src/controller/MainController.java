package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.DungeonCamera;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class MainController extends ScreenAdapter {
    private SpriteBatch batch;
    private LevelController levelController;
    private EntityController entityController;
    private DungeonCamera camera;
    private HUDController hud;

    private boolean doFirstFrame =true;

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
     * useful)
     */
    @Override
    public final void render(float delta) {
        if(doFirstFrame) this.firstFrame();

        beginFrame();
        // clears the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        levelController.update();
        entityController.update();
        camera.update();
        hud.update();
        endFrame();
    }

    private void firstFrame(){
        doFirstFrame=false;
        this.entityController=new EntityController(batch);
        //the hud needs its own batch
        this.hud=new HUDController(new SpriteBatch());
        this.camera=new DungeonCamera();
        this.levelController=new LevelController(batch);
        setup();
    }

    public void setSpriteBatch(SpriteBatch batch) {
        this.batch = batch;
    }
}
