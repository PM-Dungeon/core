package interfaces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.GraphicController;
import graphic.TextureFactory;
import org.w3c.dom.Text;
import tools.Point;

public interface IDrawable {
    public SpriteBatch getBatch();

    public Point getPosition();

    public TextureFactory getTextureFactory();

    public Texture getTexture();

    public GraphicController getGraphicController();

    default void draw(){
        getGraphicController().draw(getTexture(),getPosition(),getBatch());
    }
}
