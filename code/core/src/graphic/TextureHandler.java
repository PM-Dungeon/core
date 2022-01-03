package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.*;
import java.util.stream.Collectors;

/** Singleton, lazy initialization (possibly not thread-safe). */
public class TextureHandler {
    private static TextureHandler instance;

    private final Map<String, Set<TextureType>> prefixMap = new LinkedHashMap<>();
    private final Map<String, Set<TextureType>> nameMap = new LinkedHashMap<>();
    private final Map<String, Set<TextureType>> pathMap = new LinkedHashMap<>();

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
            TextureType t = new TextureType(fh);
            prefixMap.computeIfAbsent(t.prefix(), x -> new LinkedHashSet<>()).add(t);
            nameMap.computeIfAbsent(t.name(), x -> new LinkedHashSet<>()).add(t);
            pathMap.computeIfAbsent(t.path(), x -> new LinkedHashSet<>()).add(t);
        }
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
        return prefixMap.get(prefix).stream().map(TextureType::path).collect(Collectors.toList());
    }

    public List<String> getTexturesForName(String name) {
        return nameMap.get(name).stream().map(TextureType::path).collect(Collectors.toList());
    }

    public List<String> getTexturesForPath(String path) {
        return pathMap.get(path).stream().map(TextureType::path).collect(Collectors.toList());
    }

    public List<String> getTexturesForContainsPrefix(String prefix) {
        return getAvailablePrefixes().stream()
                .filter(k -> k.contains(prefix))
                .map(this::getTexturesForPrefix)
                .reduce(
                        new ArrayList<>(),
                        (partialList, addList) -> {
                            partialList.addAll(addList);
                            return partialList;
                        });
    }

    public List<String> getTexturesForContainsName(String name) {
        return getAvailableNames().stream()
                .filter(k -> k.contains(name))
                .map(this::getTexturesForName)
                .reduce(
                        new ArrayList<>(),
                        (partialList, addList) -> {
                            partialList.addAll(addList);
                            return partialList;
                        });
    }

    public List<String> getTexturesForContainsPath(String path) {
        return getAvailablePaths().stream()
                .filter(k -> k.contains(path))
                .map(this::getTexturesForPath)
                .reduce(
                        new ArrayList<>(),
                        (partialList, addList) -> {
                            partialList.addAll(addList);
                            return partialList;
                        });
    }
}
