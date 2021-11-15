package controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import tools.Point;

public class GraphicController {

    /**
     * Draws the instance based on its position.
     *
     * @param xOffset
     * @param yOffset
     * @param xScaling
     * @param yScaling
     * @param texture
     * @param position
     * @param batch
     */
    public void draw(
            float xOffset,
            float yOffset,
            float xScaling,
            float yScaling,
            Texture texture,
            Point position,
            SpriteBatch batch) {
        Sprite sprite = new Sprite(texture);
        // this will resize the texture. this is setuped for the textures used in the thesis
        sprite.setSize(xScaling, yScaling);
        // where to draw the sprite
        sprite.setPosition(position.x + xOffset, position.y + yOffset);

        // need to be called before drawing
        batch.begin();
        // draw sprite
        sprite.draw(batch);
        // need to be called after drawing
        batch.end();
    }

    /**
     * Draws the instance based on its position with default offset and default scaling.
     *
     * @param texture
     * @param position
     * @param batch
     */
    public void draw(Texture texture, Point position, SpriteBatch batch) {
        // found offset by try and error
        this.draw(
                -0.85f,
                -0.5f,
                1,
                ((float) texture.getHeight() / (float) texture.getWidth()),
                texture,
                position,
                batch);
    }

    /**
     * Draws the instance based on its position with default offset and specific scaling.
     *
     * @param xScaling
     * @param yScaling
     * @param texture
     * @param position
     * @param batch
     */
    public void drawWithScaling(
            float xScaling, float yScaling, Texture texture, Point position, SpriteBatch batch) {
        this.draw(-0.85f, -0.5f, xScaling, yScaling, texture, position, batch);
    }

    /**
     * Draws the instance based on its position with default scaling and specific offset
     *
     * @param xOffset
     * @param yOffset
     * @param texture
     * @param position
     * @param batch
     */
    public void draw(
            float xOffset, float yOffset, Texture texture, Point position, SpriteBatch batch) {
        this.draw(
                xOffset,
                yOffset,
                1,
                ((float) texture.getHeight() / (float) texture.getWidth()),
                texture,
                position,
                batch);
    }
}
