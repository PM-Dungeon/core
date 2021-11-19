package controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** ApplicationListener that delegates to the MainGameController. Just some setup. */
public final class GameSetup extends Game {

    /**
     * This INSTANCE is necessary to draw ALL the stuff. Every Object that uses draw need to know
     * THIS INSTANCE
     */
    private SpriteBatch batch;

    private final MainController mc;
    /** ApplicationListener that delegates to the MainGameController. Just some setup. */
    public GameSetup(MainController mc) {
        this.mc = mc;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        mc.setSpriteBatch(batch);
        this.setScreen(mc);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
