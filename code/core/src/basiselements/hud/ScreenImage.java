package basiselements.hud;

import basiselements.ScreenElement;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import tools.Constants;
import tools.Point;

public class ScreenImage extends Image implements ScreenElement {

    /**
     * Creates an Image for the UI
     *
     * @param texturePath the Path to the Texture
     * @param position the Position where the Image should be drawn
     */
    public ScreenImage(String texturePath, Point position) {
        super(new Texture(texturePath));
        this.setPosition(position.x, position.y);
        this.setScale(1 / Constants.DEFAULT_ZOOM_FACTOR);
    }

    @Override
    public Actor getActor() {
        return this;
    }
}
