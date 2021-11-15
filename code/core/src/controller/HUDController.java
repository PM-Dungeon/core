package controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import interfaces.IHUDElement;

import java.util.ArrayList;

public class HUDController extends Stage {
    private GraphicController graphicController;
    private OrthographicCamera hudCamera;
    private SpriteBatch batch;
    private ArrayList<IHUDElement> elements;

    public HUDController(SpriteBatch batch, GraphicController graphicController) {
        super(new ScreenViewport(), batch);
        this.batch = batch;
        hudCamera = new OrthographicCamera();
        hudCamera.position.set(0, 0, 0);
        hudCamera.update();
        elements = new ArrayList<>();
        this.graphicController = graphicController;
    }

    public void addElement(IHUDElement element) {
        this.elements.add(element);
    }

    public void removeElement(IHUDElement element) {
        if (elements.contains(element)) elements.remove(element);
    }

    public ArrayList<IHUDElement> getElements() {
        return this.elements;
    }

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
