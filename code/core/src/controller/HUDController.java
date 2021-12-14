package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.HUDCamera;
import interfaces.IHUDElement;
import tools.AbstractDefaultController;

public class HUDController extends AbstractDefaultController<IHUDElement> {
    private final HUDCamera hudCamera;
    private final SpriteBatch batch;

    /**
     * Keeps a set of HUD elements and makes sure they are drawn.
     *
     * @param batch the batch for the HUD
     */
    public HUDController(SpriteBatch batch, HUDCamera camera) {
        this.batch = batch;
        hudCamera = camera;
        hudCamera.getPosition().set(0, 0, 0);
        hudCamera.update();
    }

    /** Redraws the HUD and all HUD elements. */
    @Override
    public void update() {
        hudCamera.update();
        batch.setProjectionMatrix(hudCamera.combined);
        drawElements();
    }

    private void drawElements() {
        set.forEach(element -> element.draw(batch));
    }
}
