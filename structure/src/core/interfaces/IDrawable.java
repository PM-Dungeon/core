package core.interfaces;
import core.graphic.DungeonTexture;
import core.tools.Point;

public interface IDrawable {

    /** @return the exact position in the dungeon of this instance */
    Point getPosition();

    /** @return the (current)texture of the object. */
    DungeonTexture getTexture();

    /**
     * Draws the instance based on its position.
     *
     * @param xOffset sometimes it can be helpful to use a small offset
     * @param yOffset sometimes it can be helpful to use a small offset
     * @param xScaling scaling x-axis
     * @param yScaling scaling y-axis
     */
    default void draw(float xOffset, float yOffset, float xScaling, float yScaling) {
       //TODO
    }

    /** Draws the instance based on its position with default offset and default scaling. */
    default void draw() {
        // found offset by try and error
        DungeonTexture texture = this.getTexture();
        this.draw(-0.85f, -0.5f, 1, ((float) texture.getHeight() / (float) texture.getWidth()));
    }

    /**
     * Draws the instance based on its position with default offset and specific scaling.
     *
     * @param xScaling scaling x-axis
     * @param yScaling scaling y-axis
     */
    default void drawWithScaling(float xScaling, float yScaling) {
        this.draw(-0.85f, -0.5f, xScaling, yScaling);
    }

    /**
     * Draws the instance based on its position with default scaling and specific offset
     *
     * @param xOffset offset x-axis
     * @param yOffset offset y-axis
     */
    default void draw(float xOffset, float yOffset) {
        DungeonTexture texture = this.getTexture();
        this.draw(xOffset, yOffset, 1, ((float) texture.getHeight() / (float) texture.getWidth()));
    }


}
