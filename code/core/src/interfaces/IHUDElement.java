package interfaces;

import com.badlogic.gdx.graphics.Texture;
import tools.Point;

/* Should be implemented by all hud objects */
public interface IHUDElement {
    /**
     * The position of hud elements are based on virtual coordinates.
     *
     * @return the position
     */
    Point getPosition();

    Texture getTexture();

    default float getWidth() {
        return 0.5f;
    }

    default float getHeight() {
        return getTexture().getHeight() / 2f;
    }
}
