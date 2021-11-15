package interfaces;

import com.badlogic.gdx.graphics.Texture;
import graphic.TextureFactory;
import tools.Point;
/** Should be implement by all hud objects */
public interface IHUDElement {
    /**
     * The position of hud elements are based on virtual coordinates.
     *
     * @return
     */
    public Point getPosition();

    public Texture getTexture();
    /**
     * Each hud-element should use this controller to draw itself
     *
     * @return
     */
    public TextureFactory getTextureFactory();

    default float getWidth() {
        return 0.5f;
    }

    default float getHeight() {
        return getTexture().getHeight()/2f;
    }
}
