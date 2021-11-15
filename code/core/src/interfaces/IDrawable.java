package interfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.GraphicController;
import graphic.TextureFactory;
import tools.Point;
/** Should be implement by all objects that are drawable but have no animation */
public interface IDrawable {
    public SpriteBatch getBatch();
    /** @return the exact position in the dungeon of this instance */
    public Point getPosition();
    /** Each drawable should use a factory to create textures */
    public TextureFactory getTextureFactory();
    /** @return the (current)texture of the object. */
    public Texture getTexture();

    /**
     * Each drawable should use this controller to draw itself
     *
     * @return
     */
    public GraphicController getGraphicController();

    /** draws this instance on the spritebatch */
    default void draw() {
        getGraphicController().draw(getTexture(), getPosition(), getBatch());
    }
}
