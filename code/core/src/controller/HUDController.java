package controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import interfaces.IHUDElement;

import java.util.ArrayList;

/** Keeps a list of Hud elements and makes sure they are drawn */
public class HUDController extends Stage {
    private GraphicController graphicController;
    private OrthographicCamera hudCamera;
    private SpriteBatch batch;
    private ArrayList<IHUDElement> elements;

    /**
     * Keeps a list of Hud elements and makes sure they are drawn
     *
     * @param batch batch for the hud
     * @param graphicController
     */
    public HUDController(SpriteBatch batch, GraphicController graphicController) {
        super(new ScreenViewport(), batch);
        this.batch = batch;
        hudCamera = new OrthographicCamera();
        hudCamera.position.set(0, 0, 0);
        hudCamera.update();
        elements = new ArrayList<>();
        this.graphicController = graphicController;
    }

    /**
     * Adds an element to the HUD
     *
     * @param element
     */
    public void addElement(IHUDElement element) {
        if (element == null) throw new IllegalArgumentException("null can not be added.");
        if (!elements.contains(element)) this.elements.add(element);
    }

    /**
     * Removes an element from the HUD
     *
     * @param element
     */
    public void removeElement(IHUDElement element) {
        if (element == null) throw new IllegalArgumentException("null can not be deleted.");
        if (elements.contains(element)) elements.remove(element);
    }

    public void clearHUD() {
        this.elements.clear();
    }

    /** @return List with all the elements on the hud */
    public ArrayList<IHUDElement> getElements() {
        return this.elements;
    }

    /** redraw hud and hud elements */
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
