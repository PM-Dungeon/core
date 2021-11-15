package graphic;

import com.badlogic.gdx.graphics.Texture;

/**
 * Factorypattern for Textures so we can mock it
 */
public class TextureFactory {

    public Texture getTexture(String path){
        return new Texture(path);
    }
}
