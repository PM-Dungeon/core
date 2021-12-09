package controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Drawer;
import graphic.HUDCamera;
import interfaces.IHUDElement;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HUDController implements IController<IHUDElement> {
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

    @Override
    /** Registers an element to the HUD. */
    public void add(IHUDElement element) {
        elements.add(element);
    }

    @Override
    /** Returns <code>true</code> if the element is registered. */
    public boolean contains(IHUDElement element) {
        return elements.contains(element);
    }

    @Override
    /** Removes an element from the HUD. */
    public void remove(IHUDElement element) {
        elements.remove(element);
    }

    @Override
    /** Clears all HUD elements. */
    public void removeAll() {
        elements.clear();
    }

    @Override
    /** Returns a copy set with all elements on the HUD. */
    public Set<IHUDElement> getSet() {
        return new LinkedHashSet<>(elements);
    }

    @Override
    /** Returns a copy list with all elements on the HUD. */
    public List<IHUDElement> getList() {
        return new ArrayList<>(elements);
    }

    @Override
    /** Redraws the HUD and all HUD elements. */
    public void update() {
        hudCamera.update();
        batch.setProjectionMatrix(hudCamera.combined);
        drawElements();
    }

    private void drawElements() {
        elements.forEach(
                element -> drawer.draw(element.getTexture(), element.getPosition(), batch));
    }
}
