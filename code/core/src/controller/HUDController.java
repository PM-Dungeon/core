package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.HUDCamera;
import interfaces.IHUDElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Keeps a set of HUD elements and makes sure they are drawn. */
public class HUDController {
    private final GraphicController graphicController;
    private final HUDCamera hudCamera;
    private final SpriteBatch batch;
    private final Set<IHUDElement> elements;

    /**
     * Keeps a list of Hud elements and makes sure they are drawn.
     *
     * @param batch batch for the hud
     * @param graphicController the GraphicController for the hud
     */
    public HUDController(SpriteBatch batch, GraphicController graphicController, HUDCamera camera) {
        this.batch = batch;
        hudCamera = camera;
        hudCamera.getPosition().set(0, 0, 0);
        hudCamera.update();
        elements = new HashSet<>();
        this.graphicController = graphicController;
    }

    /** Adds an element to the HUD. */
    public void addElement(IHUDElement element) {
        elements.add(element);
    }

    /** Removes an element from the HUD. */
    public void removeElement(IHUDElement element) {
        elements.remove(element);
    }

    /** Clears the entire HUD. */
    public void clearHUD() {
        elements.clear();
    }

    /** Returns a list with all elements on the HUD. */
    public List<IHUDElement> getElements() {
        return new ArrayList<>(elements);
    }

    /** Redraws the HUD and all HUD elements. */
    public void update() {
        hudCamera.update();
        batch.setProjectionMatrix(hudCamera.combined);
        drawElements();
    }

    private void drawElements() {
        elements.forEach(
                element ->
                        graphicController.draw(element.getTexture(), element.getPosition(), batch));
    }
}
