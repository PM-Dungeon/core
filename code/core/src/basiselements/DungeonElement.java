package basiselements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tools.Point;

public interface DungeonElement {
    /** Will be executed every frame. */
    void update();

    /** Draws this instance on the batch. */
    void draw();

    /**
     * @return <code>true</code>, if this instance can be deleted; <code>false</code> otherwise
     */
    default boolean removable() {
        return false;
    }

    SpriteBatch getBatch();

    /**
     * @return the exact position in the dungeon of this instance
     */
    Point getPosition();

    /**
     * @return the (current) Texture-Path of the object
     */
    String getTexturePath();
}
