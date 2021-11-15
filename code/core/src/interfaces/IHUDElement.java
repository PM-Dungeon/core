package interfaces;

import com.badlogic.gdx.graphics.Texture;
import graphic.TextureFactory;
import tools.Point;

public interface IHUDElement {

    public Point getPosition();
    public Texture getTexture();
    public TextureFactory getTextureFactory();

    default float getWidth() {
        return 0.5f;
    }

    default float getHeight() {
        return getTexture().getHeight() / 2;
    }

}
