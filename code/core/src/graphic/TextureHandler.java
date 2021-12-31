package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/** Singleton. */
public class TextureHandler {
    public static final TextureHandler HANDLER = new TextureHandler();

    private final LinkedHashMap<String, LinkedHashSet<TextureType>> prefixMap =
            new LinkedHashMap<>();
    private final LinkedHashMap<String, LinkedHashSet<TextureType>> nameMap = new LinkedHashMap<>();
    private final LinkedHashMap<String, LinkedHashSet<TextureType>> pathMap = new LinkedHashMap<>();

    private TextureHandler() {
        addAllAssets(Gdx.files.internal("character"));
        addAllAssets(Gdx.files.internal("hud"));
        addAllAssets(Gdx.files.internal("textures"));
    }

    private void addAllAssets(FileHandle fh) {
        if (fh.isDirectory()) {
            Arrays.stream(fh.list()).forEach(this::addAllAssets);
        } else {
            TextureType t = new TextureType(fh);
            prefixMap.computeIfAbsent(t.getPrefix(), x -> new LinkedHashSet<>()).add(t);
            nameMap.computeIfAbsent(t.getName(), x -> new LinkedHashSet<>()).add(t);
            pathMap.computeIfAbsent(t.getPath(), x -> new LinkedHashSet<>()).add(t);
        }
    }

    public String[] getAvailablePrefixes() {
        return prefixMap.keySet().toArray(new String[0]);
    }

    public String[] getAvailableNames() {
        return nameMap.keySet().toArray(new String[0]);
    }

    public String[] getAvailablePaths() {
        return pathMap.keySet().toArray(new String[0]);
    }

    public TextureType[] getTextureTypesForPrefix(String prefix) {
        return prefixMap.getOrDefault(prefix, new LinkedHashSet<>()).toArray(TextureType[]::new);
    }

    public TextureType[] getTextureTypesForName(String name) {
        return nameMap.getOrDefault(name, new LinkedHashSet<>()).toArray(TextureType[]::new);
    }

    public TextureType[] getTextureTypesForPath(String path) {
        return pathMap.getOrDefault(path, new LinkedHashSet<>()).toArray(TextureType[]::new);
    }
}
