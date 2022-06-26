package basiselements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tools.Point;

public interface DungeonElement extends RemovableElement {
    /** Will be executed every frame. */
    void update();

    /** Draws this instance on the batch. */
    void draw();

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
