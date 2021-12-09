package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Drawer;
import graphic.HUDCamera;
import interfaces.IHUDElement;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HUDController {
    private final Drawer drawer;
    private final HUDCamera hudCamera;
    private final SpriteBatch batch;
    private final Set<IHUDElement> elements;

    /**
     * Keeps a set of HUD elements and makes sure they are drawn.
     *
     * @param batch the batch for the HUD
     * @param drawer the <code>GraphicController</code> for the HUD
     */
    public HUDController(SpriteBatch batch, Drawer drawer, HUDCamera camera) {
        this.batch = batch;
        hudCamera = camera;
        hudCamera.getPosition().set(0, 0, 0);
        hudCamera.update();
        elements = new LinkedHashSet<>();
        this.drawer = drawer;
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

    /** Clears all HUD elements. */
    public void clearHUD() {
        elements.clear();
    }

    /** Returns a copy set with all elements on the HUD. */
    public Set<IHUDElement> getElementsSet() {
        return new LinkedHashSet<>(elements);
    }

    /** Returns a copy list with all elements on the HUD. */
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
                        drawer.draw(element.getTexture(), element.getPosition(), batch));
    }
}
