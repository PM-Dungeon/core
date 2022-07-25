package basiselements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

public abstract class Entity implements DungeonElement {

    /**
     * A object that can be controlled by the <code>EntityController
     * </code>.
     */
    public Entity() {}

    /**
     * Draws this instance on the batch.
     *
     * @param painter Painter that draws this object
     * @param batch Batch to draw on
     */
    @Override
    public void draw(Painter painter, SpriteBatch batch) {
        painter.draw(getTexturePath(), getPosition(), batch);
    }
}
