package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.HUDCamera;
import interfaces.IHUDElement;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HUDController {
    private final GraphicController graphicController;
    private final HUDCamera hudCamera;
    private final SpriteBatch batch;
    private final Set<IHUDElement> elements;

    /**
     * Keeps a set of HUD elements and makes sure they are drawn.
     *
     * @param batch the batch for the HUD
     * @param graphicController the GraphicController for the HUD
     */
    public HUDController(SpriteBatch batch, GraphicController graphicController, HUDCamera camera) {
        this.batch = batch;
        hudCamera = camera;
        hudCamera.getPosition().set(0, 0, 0);
        hudCamera.update();
        elements = new LinkedHashSet<>();
        this.graphicController = graphicController;
    }

    /** Registers an element to the HUD. */
    public void addElement(IHUDElement element) {
        elements.add(element);
    }

    /** Returns <code>true</code> if the element is registered. */
    public boolean containsElement(IHUDElement element) {
        return elements.contains(element);
    }

    /** Removes an element from the HUD. */
    public void removeElement(IHUDElement element) {
        elements.remove(element);
    }

    /** Clears the entire HUD. */
    public void clearHUD() {
        elements.clear();
    }

    // Pick that one you need:

    /** Returns a set with all elements on the HUD. */
    public Set<IHUDElement> getElementsSet() {
        return elements;
    }

    /** Returns a list with all elements on the HUD. */
    public List<IHUDElement> getElementsList() {
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
