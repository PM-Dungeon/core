package graphic;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TextureMap {
    private HashMap<String, Texture> textureMap = new HashMap();

    /**
     * Searches the HashMap for the matching texture and returns it. If the texture is not stored in
     * the HashMap, it is created and saved in.
     *
     * @param path to texture
     * @return
     */
    public Texture getTexture(String path) {
        if (textureMap.containsKey(path)) return textureMap.get(path);

        Texture texture = new Texture(path);
        textureMap.put(path, texture);
        return texture;
    }
}
