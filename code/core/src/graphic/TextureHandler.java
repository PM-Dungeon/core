package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.*;

/** Singleton, lazy initialization (possibly not thread-safe). */
public class TextureHandler {
    private static TextureHandler instance;

    private final Map<String, Set<TextureType>> prefixMap = new LinkedHashMap<>();
    private final Map<String, Set<TextureType>> nameMap = new LinkedHashMap<>();
    private final Map<String, Set<TextureType>> pathMap = new LinkedHashMap<>();

    private TextureHandler() {
        addAllAssets(Gdx.files.internal("character"));
        addAllAssets(Gdx.files.internal("hud"));
        addAllAssets(Gdx.files.internal("textures"));
    }

    public static TextureHandler getInstance() {
        if (instance == null) {
            instance = new TextureHandler();
        }
        return instance;
    }

    private void addAllAssets(FileHandle fh) {
        if (fh.isDirectory()) {
            Arrays.stream(fh.list()).forEach(this::addAllAssets);
        } else {
            TextureType t = new TextureType(fh);
            prefixMap.computeIfAbsent(t.prefix(), x -> new LinkedHashSet<>()).add(t);
            nameMap.computeIfAbsent(t.name(), x -> new LinkedHashSet<>()).add(t);
            pathMap.computeIfAbsent(t.path(), x -> new LinkedHashSet<>()).add(t);
        }
    }

    public List<String> getAvailablePrefixes() {
        return new ArrayList<>(prefixMap.keySet());
    }

    public List<String> getAvailableNames() {
        return new ArrayList<>(nameMap.keySet());
    }

    public List<String> getAvailablePaths() {
        return new ArrayList<>(pathMap.keySet());
    }

    public List<String> getTexturesForPrefix(String prefix) {
        List<String> textures = new ArrayList<>();
        prefixMap.get(prefix).forEach(t -> textures.add(t.path()));
        return textures;
    }

    public List<String> getTexturesForName(String name) {
        List<String> textures = new ArrayList<>();
        nameMap.get(name).forEach(t -> textures.add(t.path()));
        return textures;
    }

    public List<String> getTexturesForPath(String path) {
        List<String> textures = new ArrayList<>();
        pathMap.get(path).forEach(t -> textures.add(t.path()));
        return textures;
    }

    public List<String> getTexturesForContainsPrefix(String prefix) {
        List<String> textures = new ArrayList<>();
        prefixMap.keySet().stream()
                .filter(k -> k.contains(prefix))
                .forEach(k -> prefixMap.get(k).forEach(t -> textures.add(t.path())));
        return textures;
    }

    public List<String> getTexturesForContainsName(String name) {
        List<String> textures = new ArrayList<>();
        nameMap.keySet().stream()
                .filter(k -> k.contains(name))
                .forEach(k -> nameMap.get(k).forEach(t -> textures.add(t.path())));
        return textures;
    }

    public List<String> getTexturesForContainsPath(String path) {
        List<String> textures = new ArrayList<>();
        pathMap.keySet().stream()
                .filter(k -> k.contains(path))
                .forEach(k -> pathMap.get(k).forEach(t -> textures.add(t.path())));
        return textures;
    }
}
