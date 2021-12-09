package interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Drawer;
import tools.Point;

/** Should be implemented by all HUD objects. */
public interface IHUDElement {

    /**
     * The position of HUD elements are based on virtual coordinates.
     *
     * @return the position
     */
    Point getPosition();

    String getTexture();

    Drawer getGraphicController();

    default void draw(SpriteBatch batch) {
        getGraphicController().draw(getTexture(), getPosition(), batch);
    }
}
