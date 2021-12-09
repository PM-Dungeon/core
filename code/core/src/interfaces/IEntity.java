package interfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.GraphicController;
import graphic.TextureFactory;
import tools.Point;

/** Must be implemented for all objects that should be controlled by the DungeonEntityController. */
public interface IEntity {

    /** Will be executed every frame. */
    void update();

    /** @return <code>true</code>, if this instance can be deleted; <code>false</code> otherwise */
    boolean removable();

    SpriteBatch getBatch();

    /** @return The exact position in the dungeon of this instance. */
    Point getPosition();

    /** @return The (current) texture of the object. */
    Texture getTexture();

    TextureFactory getFactory();

    /** Each drawable should use this controller to draw itself. */
    GraphicController getGraphicController();

    /** Draws this instance on the batch. */
    default void draw() {
        getGraphicController().draw(getTexture(), getPosition(), getBatch());
    }
}
