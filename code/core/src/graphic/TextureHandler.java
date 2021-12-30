package graphic;

import java.util.LinkedHashMap;

/** Singleton. */
public class TextureHandler {
    public static final TextureHandler HANDLER = new TextureHandler();

    private final LinkedHashMap<String, TextureType> textureTypes = new LinkedHashMap<>();

    private TextureHandler() {
        addTextureType(new TextureType("hero", "character/knight"));
        addTextureType(new TextureType("monster", "character/monster"));
    }

    public void addTextureType(TextureType textureType) {
        textureTypes.put(textureType.getName(), textureType);
    }

    public String[] getAvailableNames() {
        return textureTypes.keySet().toArray(new String[0]);
    }

    public TextureType2[] getTexturesForName(String name) {
        if (textureTypes.containsKey(name)) {
            return textureTypes.get(name).getAvailableTextures();
        }
        return null;
    }
}
