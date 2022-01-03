package textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.*;
import java.util.stream.Collectors;

/** Singleton, lazy initialization (possibly not thread-safe). */
public class TextureHandler {
    private static TextureHandler instance;

    private final Map<String, Set<TextureWrapper>> prefixMap = new LinkedHashMap<>();
    private final Map<String, Set<TextureWrapper>> nameMap = new LinkedHashMap<>();
    private final Map<String, Set<TextureWrapper>> pathMap = new LinkedHashMap<>();

    private TextureHandler() {
        addAllAssets(Gdx.files.internal(Gdx.files.getLocalStoragePath()));
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
            TextureWrapper w = new TextureWrapper(fh);
            prefixMap.computeIfAbsent(w.prefix(), x -> new LinkedHashSet<>()).add(w);
            nameMap.computeIfAbsent(w.name(), x -> new LinkedHashSet<>()).add(w);
            pathMap.computeIfAbsent(w.path(), x -> new LinkedHashSet<>()).add(w);
        }
    }

    public String getPrefix(FileHandle fh) {
        return fh.parent().path();
    }

    public Set<String> getAvailablePrefixes() {
        return prefixMap.keySet();
    }

    public Set<String> getAvailableNames() {
        return nameMap.keySet();
    }

    public Set<String> getAvailablePaths() {
        return pathMap.keySet();
    }

    public List<String> getTexturesForPrefix(String prefix) {
        return prefixMap.get(prefix).stream()
                .map(TextureWrapper::path)
                .collect(Collectors.toList());
    }

    public List<String> getTexturesForName(String name) {
        return nameMap.get(name).stream().map(TextureWrapper::path).collect(Collectors.toList());
    }

    public List<String> getTexturesForPath(String path) {
        return pathMap.get(path).stream().map(TextureWrapper::path).collect(Collectors.toList());
    }

    public List<String> getTexturesForContainsPrefix(String prefix) {
        return getAvailablePrefixes().stream()
                .filter(k -> k.contains(prefix))
                .map(this::getTexturesForPrefix)
                .collect(ArrayList::new, List::addAll, List::addAll);
    }

    public List<String> getTexturesForContainsName(String name) {
        return getAvailableNames().stream()
                .filter(k -> k.contains(name))
                .map(this::getTexturesForName)
                .collect(ArrayList::new, List::addAll, List::addAll);
    }

    public List<String> getTexturesForContainsPath(String path) {
        return getAvailablePaths().stream()
                .filter(k -> k.contains(path))
                .map(this::getTexturesForPath)
                .collect(ArrayList::new, List::addAll, List::addAll);
    }
}
